package com.yangliuxin.controller.wechat;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangliuxin.bean.LotteryBean;
import com.yangliuxin.bean.ReserveBean;
import com.yangliuxin.bean.ShopBean;
import com.yangliuxin.config.PropertyConfiguration;
import com.yangliuxin.domain.*;
import com.yangliuxin.enums.TopShopDataEnum;
import com.yangliuxin.exceptions.WeChatAuthorizeException;
import com.yangliuxin.property.GiftProperty;
import com.yangliuxin.property.WeChatAccountProperty;
import com.yangliuxin.repository.*;
import com.yangliuxin.utils.CookieUtil;
import com.yangliuxin.utils.LotteryUtil;
import com.yangliuxin.utils.SignUtil;
import com.yangliuxin.vo.Gift;
import com.yangliuxin.vo.ResultVo;
import com.yangliuxin.vo.StatsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Controller
@RequestMapping("/wechat")
@Slf4j
@CrossOrigin
@Api(tags = "VIVO活动接口", description = "微信相关接口API")
public class WeChatController {

    @Autowired
    private WxMpService wxMpService;


    @Autowired
    private WeChatAccountProperty weChatAccountProperty;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation(value = "授权跳转，禁止调用")
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String url = weChatAccountProperty.getSiteUrl()+"wechat/callBack";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    @ApiOperation(value = "授权回调，禁止调用")
    @GetMapping("/callBack")
    public String callBack(HttpServletRequest request,  HttpServletResponse response,@RequestParam("code") String code, @RequestParam("state") String returnUrl) throws Exception {
        //token redis
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken ;
        wxMpOAuth2AccessToken = (WxMpOAuth2AccessToken)redisTemplate.opsForValue().get("WX_ACCESS_TOKEN");
        Long expireIn = (Long)redisTemplate.opsForValue().get("WX_ACCESS_TOKEN_EXPIRE");
        if(null == wxMpOAuth2AccessToken || null == expireIn || expireIn < System.currentTimeMillis()){
            try {
                wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
                redisTemplate.opsForValue().set("WX_ACCESS_TOKEN", wxMpOAuth2AccessToken);
                redisTemplate.opsForValue().set("WX_ACCESS_TOKEN_EXPIRE",System.currentTimeMillis()+7000000);
            } catch (WxErrorException e) {
                throw new WeChatAuthorizeException("wechat/index");
            }
        }

        //String openId = wxMpOAuth2AccessToken.getOpenId();
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken,"zh_CN");
        if(StringUtils.isEmpty(wxMpUser.getOpenId())) throw  new WeChatAuthorizeException("wechat/index");
        String openId = wxMpUser.getOpenId();

        Users dbUsers = usersRepository.getUserByOpenId(openId);
        Users users = new Users();
        if(dbUsers == null){
            users.setCity(wxMpUser.getCity() != null ? wxMpUser.getCity() : "");
            users.setProvince(wxMpUser.getProvince() != null ? wxMpUser.getProvince() : "");
            users.setCountry(wxMpUser.getCountry() != null ? wxMpUser.getCountry() : "");
            users.setHeadimgurl(wxMpUser.getHeadImgUrl() != null ? wxMpUser.getHeadImgUrl() : "");
            users.setNickname(wxMpUser.getNickname() != null ? wxMpUser.getNickname() : "");
            users.setOpenId(wxMpUser.getOpenId() != null ? wxMpUser.getOpenId() : "");
            users.setSex(wxMpUser.getSex() != null ? wxMpUser.getSex() : "");
            users.setCreateTime(new Date());
            users.setUpdateTime(new Date());
            users = usersRepository.save(users);
        }
        else{
            dbUsers.setHeadimgurl(wxMpUser.getHeadImgUrl() != null ? wxMpUser.getHeadImgUrl() : "");
            dbUsers.setNickname(wxMpUser.getNickname() != null ? wxMpUser.getNickname() : "");
            users = usersRepository.save(dbUsers);
        }
        String cookieValue = URLEncoder.encode(objectMapper.writeValueAsString(users),"UTF-8");
        CookieUtil.set(response, weChatAccountProperty.getToken(), cookieValue, weChatAccountProperty.getExpire());
        return "redirect:" + returnUrl;
    }

    @GetMapping("index")
    @ApiOperation(value = "活动入口")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws WeChatAuthorizeException, IOException {
        Cookie cookie = CookieUtil.get(request,weChatAccountProperty.getToken());
        if(null == cookie){
            throw new WeChatAuthorizeException("wechat/index");
        }

        String usersJson = URLDecoder.decode(cookie.getValue(), "UTF-8");
        Users users = objectMapper.readValue(usersJson,Users.class);
        if(users == null || users.getOpenId() == null || usersRepository.getUserByOpenId(users.getOpenId()) == null){
            throw new WeChatAuthorizeException("wechat/index");
        }
        System.out.println(users);
        ModelAndView mv = new ModelAndView("wechat");
        return mv;
    }


    @GetMapping("getJsApiTicket")
    @ResponseJSONP
    @ApiOperation(value = "获取分享票据签名")
    public ResultVo<WxJsapiSignature> getJsApiTicket(@RequestParam("url") String url) throws WxErrorException {
        ResultVo<WxJsapiSignature> resultVo = new ResultVo<>();
        if(url == null) url = "";
        WxJsapiSignature wxJsapiSignature = wxMpService.createJsapiSignature(url);
        System.out.println(wxJsapiSignature);
        resultVo.setCode(1);
        resultVo.setData(wxJsapiSignature);
        return resultVo;
    }

    @Autowired
    private GiftProperty giftProperty;

    @Autowired
    private LotteryRepository lotteryRepository;

    @GetMapping("lottery")
    @ResponseJSONP
    @ApiOperation(value = "抽奖接口")
    public ResultVo<Lottery> lottery(HttpServletRequest request, HttpServletResponse response) throws WeChatAuthorizeException,Exception{
        ResultVo<Lottery> resultVo = new ResultVo<>();
        Cookie cookie = CookieUtil.get(request,weChatAccountProperty.getToken());
        if(null == cookie){
            resultVo.setCode(0);
            resultVo.setMsg("请先登录");
            return resultVo;
        }

        String usersJson = URLDecoder.decode(cookie.getValue(), "UTF-8");
        Users users = objectMapper.readValue(usersJson,Users.class);
        if(users == null || users.getOpenId() == null || usersRepository.getUserByOpenId(users.getOpenId()) == null){
            resultVo.setCode(0);
            resultVo.setMsg("请先登录");
            return resultVo;
        }

        List<Gift> gifts = giftProperty.getGiftList();
        int originalIndex = LotteryUtil.lottery(gifts);
        if(originalIndex == 0){
            resultVo.setCode(0);
            resultVo.setMsg("未中奖");
            return resultVo;
        }
        Gift currentGift = getCurrentGiftById(originalIndex);
        Long giftDayCount = redisTemplate.opsForValue().increment("GIFT_DAY"+originalIndex+LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")), 1L);
        Long giftTotalCount = redisTemplate.opsForValue().increment("GIFT_TOTAL"+originalIndex+LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")), 1L);
        if(giftDayCount >= currentGift.getPerDay()){
            resultVo.setCode(0);
            resultVo.setMsg("未中奖");
            return resultVo;
        }

        if(giftTotalCount >= currentGift.getTotal()){
            resultVo.setCode(0);
            resultVo.setMsg("未中奖");
            return resultVo;
        }

        Lottery lottery = new Lottery();
        lottery.setIndex(originalIndex);
        lottery.setUserId(Integer.parseInt(users.getId().toString()));
        lottery = lotteryRepository.save(lottery);

        resultVo.setCode(1);
        resultVo.setMsg("请求成功");
        resultVo.setData(lottery);
        return resultVo;
    }

    private Gift getCurrentGiftById(Integer id){
        for(Gift gift: giftProperty.getGiftList()){
            if(gift.getId() == id){
                return gift;
            }
        }
        return null;
    }


    @GetMapping("userInfo")
    @ResponseJSONP
    @ApiOperation(value = "获取当前用户信息")
    public ResultVo<Users> userInfo(HttpServletRequest request, HttpServletResponse response) throws WeChatAuthorizeException,Exception{
        ResultVo<Users> resultVo = new ResultVo<>();
        Cookie cookie = CookieUtil.get(request,weChatAccountProperty.getToken());
        if(null == cookie){
            resultVo.setCode(0);
            resultVo.setMsg("请先登录");
            return resultVo;
        }

        String usersJson = URLDecoder.decode(cookie.getValue(), "UTF-8");
        Users users = objectMapper.readValue(usersJson,Users.class);
        if(users == null || users.getOpenId() == null || usersRepository.getUserByOpenId(users.getOpenId()) == null){
            resultVo.setCode(0);
            resultVo.setMsg("请先登录");
            return resultVo;
        }

        resultVo.setCode(1);
        resultVo.setMsg("请求成功");
        resultVo.setData(users);
        return resultVo;


    }

    @PostMapping("saveLottery")
    @ResponseJSONP
    @ApiOperation(value = "保存抽奖信息接口")
    public ResultVo<Lottery> saveLottery(HttpServletRequest request, HttpServletResponse response,@RequestBody @Valid LotteryBean lotteryBean) throws Exception{
        ResultVo<Lottery> resultVo = new ResultVo<>();
        Cookie cookie = CookieUtil.get(request,weChatAccountProperty.getToken());
        if(null == cookie){
            resultVo.setCode(0);
            resultVo.setMsg("请先登录");
            return resultVo;
        }

        String usersJson = URLDecoder.decode(cookie.getValue(), "UTF-8");
        Users users = objectMapper.readValue(usersJson,Users.class);
        if(users == null || users.getOpenId() == null || usersRepository.getUserByOpenId(users.getOpenId()) == null){
            resultVo.setCode(0);
            resultVo.setMsg("请先登录");
            return resultVo;
        }

        Lottery lottery = lotteryRepository.getById(lotteryBean.getId());
        if(lottery == null || !users.getId().equals(lotteryBean.getUserId()) || !lottery.getUserId().equals(lotteryBean.getUserId())  || !lottery.getIndex().equals(lotteryBean.getIndex())){
            resultVo.setCode(0);
            resultVo.setMsg("不存在的中奖信息");
            return resultVo;
        }

        lottery.setAddress(lotteryBean.getAddress());
        lottery.setMobile(lotteryBean.getMobile());
        lottery.setName(lotteryBean.getName());
        lottery.setShopId(lotteryBean.getShopId());
        lottery = lotteryRepository.save(lottery);

        resultVo.setCode(1);
        resultVo.setMsg("请求成功");
        resultVo.setData(lottery);
        return resultVo;
    }

    @Autowired
    private ReserveRepository reserveRepository;

    @PostMapping("saveReserve")
    @ResponseJSONP
    @ApiOperation(value = "保存预定信息接口")
    public ResultVo<Reserve> saveReserve(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid ReserveBean reserveBean) throws Exception{
        ResultVo<Reserve> resultVo = new ResultVo<>();
        Cookie cookie = CookieUtil.get(request,weChatAccountProperty.getToken());
        if(null == cookie){
            resultVo.setCode(0);
            resultVo.setMsg("请先登录");
            return resultVo;
        }

        String usersJson = URLDecoder.decode(cookie.getValue(), "UTF-8");
        Users users = objectMapper.readValue(usersJson,Users.class);
        if(users == null || users.getOpenId() == null || usersRepository.getUserByOpenId(users.getOpenId()) == null){
            resultVo.setCode(0);
            resultVo.setMsg("请先登录");
            return resultVo;
        }

        Reserve reserve = new Reserve();
        reserve.setShopId(reserveBean.getShopId());
        reserve.setMobile(reserveBean.getMobile());
        reserve.setProduct(reserveBean.getProduct());
        reserve.setShop(reserveBean.getShop());
        reserve.setName(reserveBean.getName());
        reserve = reserveRepository.save(reserve);

        resultVo.setCode(1);
        resultVo.setMsg("请求成功");
        resultVo.setData(reserve);
        return resultVo;
    }

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("getShopData")
    @ResponseJSONP
    @ApiOperation(value = "根据店铺号码获取店铺当天数据信息")
    public ResultVo<Shop> getShopData(@RequestParam("shopId") @NotNull @Valid String shopId){
        ResultVo<Shop> resultVo = new ResultVo<>();
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Shop shop = shopRepository.getShopData(shopId, today);
        resultVo.setCode(1);
        resultVo.setMsg("请求成功");
        resultVo.setData(shop);
        return resultVo;
    }

    @GetMapping("getTopShopData")
    @ResponseJSONP
    @ApiOperation(value = "获取当天各排名数据")
    public ResultVo<List<Shop>> getTopShopData(@RequestParam("brand") @NotNull @NotBlank @Valid TopShopDataEnum brand){
        ResultVo<List<Shop>> resultVo = new ResultVo<>();
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<Shop> list = shopRepository.getTopShopData(brand.getCode(), today);
        resultVo.setCode(1);
        resultVo.setMsg("请求成功");
        resultVo.setData(list);
        return resultVo;
    }
    @Autowired
    private PropertyConfiguration propertyConfiguration;

    @PostMapping("importData")
    @ResponseJSONP
    @ApiImplicitParam(paramType="header", name = "sign", value = "签名", required = true, dataType = "String")
    @ApiOperation(value = "店面数据导入接口")
    public ResultVo<Shop> importData(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid ShopBean shopBean, @RequestHeader @Valid @NotNull @NotBlank String sign) throws Exception{
        SortedMap<String, String> params = new TreeMap<>();
        params.put("shopId",shopBean.getShopId());
        params.put("shopName",shopBean.getShopName());
        params.put("level",shopBean.getLevel());
        params.put("address",shopBean.getAddress());
        params.put("province",shopBean.getProvince());
        params.put("day",shopBean.getDay().toString());
        params.put("dayCountryCount",shopBean.getDayCountryCount().toString());
        params.put("dayProvinceCount",shopBean.getDayProvinceCount().toString());
        params.put("week",shopBean.getDayProvinceCount().toString());
        params.put("weekCountryCount",shopBean.getWeekCountryCount().toString());
        params.put("weekProvinceCount",shopBean.getWeekProvinceCount().toString());
        params.put("spring",shopBean.getSpring().toString());
        params.put("springCountryCount",shopBean.getSpringCountryCount().toString());
        params.put("springProvinceCount",shopBean.getWeekProvinceCount().toString());
        params.put("percent",shopBean.getSpring().toString());
        params.put("ddd",shopBean.getDdd());

        if(!sign.equals(SignUtil.getSign(params,propertyConfiguration.getToken().getJwtSecret()))){
            log.info("IMPORT_DATA_RAW:"+shopBean);
            log.info("IMPORT_DATA_SN:"+sign);
            log.info("IMPORT_DATA_RIGHT_SN:"+SignUtil.getSign(params,propertyConfiguration.getToken().getJwtSecret()));
            log.info("over one two three four");
            throw new Exception("签名失败");
        }

        ResultVo<Shop> resultVo = new ResultVo<>();
        //Shop shop = new Shop(shopBean);
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Shop shop = shopRepository.getShopData(shopBean.getShopId(), today);
        if(shop != null){
            resultVo.setCode(0);
            resultVo.setMsg("重复数据");
            return resultVo;
        }
        shop = new Shop(shopBean);
        shop = shopRepository.save(shop);
        resultVo.setCode(1);
        resultVo.setMsg("导入成功");
        resultVo.setData(shop);
        return resultVo;
    }

    @Autowired
    private PraiseRepository praiseRepository;

    @PostMapping("savePraise")
    @ResponseJSONP
    @ApiOperation(value = "点赞接口")
    public ResultVo<Praise> savePraise(HttpServletRequest request, HttpServletResponse response, @RequestParam @NotBlank @NotNull @Valid String  shopId) throws Exception{
        ResultVo<Praise> resultVo = new ResultVo<>();
        Cookie cookie = CookieUtil.get(request,weChatAccountProperty.getToken());
        if(null == cookie){
            resultVo.setCode(0);
            resultVo.setMsg("请先登录");
            return resultVo;
        }

        String usersJson = URLDecoder.decode(cookie.getValue(), "UTF-8");
        Users users = objectMapper.readValue(usersJson,Users.class);
        if(users == null || users.getOpenId() == null || usersRepository.getUserByOpenId(users.getOpenId()) == null){
            resultVo.setCode(0);
            resultVo.setMsg("请先登录");
            return resultVo;
        }

        Praise praise = new Praise();
        praise.setShopId(shopId);
        praise.setUserId(users.getId().intValue());
        praise = praiseRepository.save(praise);

        resultVo.setCode(1);
        resultVo.setMsg("请求成功");
        resultVo.setData(praise);
        return resultVo;
    }

    @GetMapping("stats")
    @ResponseJSONP
    @ApiOperation(value = "获取门店点赞 获奖数据接口")
    public ResultVo<StatsVo> stats(@RequestParam @NotNull @NotBlank @Valid String shopId){
        ResultVo<StatsVo> resultVo = new ResultVo<>();
        StatsVo statsVo = new StatsVo();

        Long praiseCount = praiseRepository.getCountByShopId(shopId);
        Long reserveCount = reserveRepository.getCountByShopId(shopId);
        List<Reserve> reserveList = reserveRepository.getListByShopId(shopId);
        List<Lottery> lotteryList = lotteryRepository.getListByShopId(shopId);

        statsVo.setLotteryList(lotteryList);
        statsVo.setPraiseCount(praiseCount);
        statsVo.setReserveCount(reserveCount);
        statsVo.setReserveList(reserveList);

        resultVo.setCode(1);
        resultVo.setMsg("请求成功");
        resultVo.setData(statsVo);
        return resultVo;
    }



}

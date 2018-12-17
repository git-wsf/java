package com.yangliuxin.domain;


import com.yangliuxin.bean.ShopBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_shop")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Shop  extends BaseEntity<Long> {

    @Column(name = "shopId")
    @ApiModelProperty("店铺编号")
    private String shopId;

    @Column(name = "shopName")
    @ApiModelProperty("店铺名称")
    private String shopName;

    @Column(name = "level")
    @ApiModelProperty("店铺级别")
    private String level;

    @Column(name = "address")
    @ApiModelProperty("店铺地址")
    private String address;

    @Column(name = "province")
    @ApiModelProperty("所在省份")
    private String province;

    @Column(name = "day")
    @ApiModelProperty("日得分")
    private Integer day;

    @Column(name = "dayCountryCount")
    @ApiModelProperty("日得分国家排行")
    private Integer dayCountryCount;

    @Column(name = "dayProvinceCount")
    @ApiModelProperty("日得分省排行")
    private Integer dayProvinceCount;

    @Column(name = "week")
    @ApiModelProperty("双周得分")
    private Integer week;

    @Column(name = "weekCountryCount")
    @ApiModelProperty("双周得分国家排行")
    private Integer weekCountryCount;

    @Column(name = "weekProvinceCount")
    @ApiModelProperty("双周得分省排行")
    private Integer weekProvinceCount;

    @Column(name = "spring")
    @ApiModelProperty("元春得分")
    private Integer spring;

    @Column(name = "springCountryCount")
    @ApiModelProperty("元春得分国家排行")
    private Integer springCountryCount;

    @Column(name = "springProvinceCount")
    @ApiModelProperty("元春得分省排行")
    private Integer springProvinceCount;

    @Column(name = "percent")
    @ApiModelProperty("百分比")
    private String percent;

    @Column(name = "ddd")
    @ApiModelProperty("日期-格式形如20181231")
    private String ddd;

    public Shop(ShopBean shopBean){
        shopId = shopBean.getShopId();
        shopName = shopBean.getShopName();
        level = shopBean.getLevel();
        address = shopBean.getAddress();
        province = shopBean.getProvince();
        day = shopBean.getDay();
        week = shopBean.getWeek();
        spring = shopBean.getSpring();
        dayCountryCount = shopBean.getDayCountryCount();
        dayProvinceCount = shopBean.getDayProvinceCount();
        weekCountryCount = shopBean.getWeekCountryCount();
        weekProvinceCount = shopBean.getWeekProvinceCount();
        springCountryCount = shopBean.getSpringCountryCount();
        springProvinceCount = shopBean.getSpringProvinceCount();
        percent = shopBean.getPercent();
        ddd = shopBean.getDdd();

    }

}

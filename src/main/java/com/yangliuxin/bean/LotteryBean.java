package com.yangliuxin.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("保存中奖信息请求bean")
public class LotteryBean {

    @NotNull
    @ApiParam("中奖id")
    private Long id;

    @NotNull
    @ApiParam("中奖等级")
    private Integer ind;

    @NotNull
    @ApiParam("中奖用户id")
    private Integer userId;

    @NotNull
    @ApiParam("姓名")
    private String name;

    @NotNull
    @ApiParam("地址")
    private String address;

    @NotNull
    @ApiParam("手机号")
    private String mobile;

    @NotNull
    @ApiParam("所属门店编号")
    private String shopId;
}

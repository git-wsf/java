package com.yangliuxin.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("保存预约信息请求bean")
public class ReserveBean {

    @NotNull
    @ApiParam("门店编号")
    private String shopId;

    @NotNull
    @ApiParam("门店名称")
    private String shop;

    @NotNull
    @ApiParam("预约产品名称")
    private String product;

    @NotNull
    @ApiParam("预约人名称")
    private String name;

    @NotNull
    @ApiParam("预约人手机号")
    private String mobile;


}

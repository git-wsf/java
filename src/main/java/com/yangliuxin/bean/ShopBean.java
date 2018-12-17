package com.yangliuxin.bean;


import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("店面数据请求实体")
public class ShopBean {
    @NotNull
    @ApiModelProperty("门店编号")
    private String shopId;

    @NotNull
    @ApiModelProperty("门店名称")
    private String shopName;

    @NotNull
    @ApiModelProperty("门店级别")
    private String level;

    @NotNull
    @ApiModelProperty("门店地址")
    private String address;

    @NotNull
    @ApiModelProperty("日得分")
    private Integer day;

    @NotNull
    @ApiModelProperty("日得分全国排名")
    private Integer dayCountryCount;

    @NotNull
    @ApiModelProperty("日得分省排名")
    private Integer dayProvinceCount;

    @NotNull
    @ApiModelProperty("双周得分")
    private Integer week;

    @NotNull
    @ApiModelProperty("双周得分全国排名")
    private Integer weekCountryCount;

    @NotNull
    @ApiModelProperty("双周得分省排名")
    private Integer weekProvinceCount;

    @NotNull
    @ApiModelProperty("双周得分级别排名")
    private Integer weekLevelCount;

    @NotNull
    @ApiModelProperty("元春得分")
    private Integer spring;

    @NotNull
    @ApiModelProperty("元春得分全国排名")
    private Integer springCountryCount;

    @NotNull
    @ApiModelProperty("元春得分省排名")
    private Integer springProvinceCount;

    @NotNull
    @ApiModelProperty("元春得分所在级别排名")
    private Integer springLevelCount;

    @NotNull
    @ApiModelProperty("击败其他分店百分比")
    private String percent;

    @NotNull
    @ApiModelProperty("日期格式形如20181218的字串")
    private String ddd;
}

package com.yangliuxin.vo;

import com.yangliuxin.enums.Sex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("数据整理模型")
public class BiBean {

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("地区")
    private String district;

    @ApiModelProperty("第三方账号ID")
    private String snsId;

    @ApiModelProperty("头像URL")
    private String headUrl;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("性别")
    private Sex sex;
}
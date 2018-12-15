package com.yangliuxin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("手机登录bean")
public class SmsCodeLoginBean{

    @ApiModelProperty("手机号码")
    @NotNull
    @NotBlank
    @NotEmpty
    private String mobile;

    @ApiModelProperty("短信验证码")
    @NotNull
    @NotBlank
    @NotEmpty
    private String smsCode;

    @ApiModelProperty("短信验证码")
    @NotNull
    @NotBlank
    @NotEmpty
    private String deviceId;

}
package com.yangliuxin.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class AppVersionVo {


    @ApiModelProperty("IOS App版本号")
    private String IosAppVersion;

    @ApiModelProperty("安卓 App版本号")
    private String AndroidAppVersion;
}

package com.yangliuxin.bean;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("保存预约信息请求bean")
public class ReserveBean {

    @NotNull
    private String shop;
    @NotNull
    private String product;
    @NotNull
    private String name;
    @NotNull
    private String address;
}

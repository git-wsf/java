package com.yangliuxin.bean;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("保存中奖信息请求bean")
public class LotteryBean {

    @NotNull
    private Long id;
    @NotNull
    private Integer index;
    @NotNull
    private Integer userId;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String mobile;
}

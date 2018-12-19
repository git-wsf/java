package com.yangliuxin.vo;

import com.yangliuxin.domain.Lottery;
import com.yangliuxin.domain.Reserve;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("门店相关统计数据")
public class StatsVo {

    @ApiModelProperty("点赞数")
    private Long praiseCount;

    @ApiModelProperty("预约数")
    private Long reserveCount;

    @ApiModelProperty("预约列表")
    private List<Reserve> reserveList;

    @ApiModelProperty("中奖列表")
    private List<Lottery> lotteryList;

}

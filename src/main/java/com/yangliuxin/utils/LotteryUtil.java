package com.yangliuxin.utils;

import com.yangliuxin.vo.Gift;
import java.util.List;
import java.util.Random;


public class LotteryUtil {


    public static int lottery(List<Gift> lotteryInfo){
        double checkValue = 0;
        double code = new Random().nextDouble();
        for (Gift value : lotteryInfo) {
            checkValue += value.getProbability();
            if (code <= checkValue) {
                return value.getId();
            }
        }
        return 0;
    }



}

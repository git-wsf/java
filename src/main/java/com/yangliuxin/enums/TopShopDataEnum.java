package com.yangliuxin.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum TopShopDataEnum {

    DayCountryCount(1,"日全国排名"),
    DayProvinceCount(2,"日全省排名"),
    WeekCountryCount(3,"双周全国排名"),
    WeekProvinceCount(4,"双周全省排名"),
    SpringCountryCount(6,"元春全省排名"),
    SpringProvinceCount(7,"元春全省排名");

    private Integer code;
    private String value;

    public String getValue(){
        return value;
    }

    public Integer getCode(){
        return code;
    }

}

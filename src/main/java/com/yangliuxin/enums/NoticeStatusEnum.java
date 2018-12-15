package com.yangliuxin.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NoticeStatusEnum {

    DRAFT(0),PUBLISH(1);

    private Integer value;

    public Integer getValue(){
        return value;
    }
}

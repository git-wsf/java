package com.yangliuxin.enums;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatusEnum {

    DISABLED(0),VALID(1),LOCKED(2);

    private Integer value;

    public Integer getValue(){
        return value;
    }

}

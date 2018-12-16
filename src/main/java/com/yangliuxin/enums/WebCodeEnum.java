package com.yangliuxin.enums;

import org.springframework.util.StringUtils;

public enum WebCodeEnum {
    ERROR(-1),
    SUCCESS(1),
    NO_DATA(40001, "无法获取数据"),
    LACK_REQUEST_PARAM(41003, "缺少参数"),
    TOKEN_EXPIRED(42002),
    SENSITIVE_WORD(45001, "内容含有敏感词，请修改。"),
    NOT_MATCH(45002),
    FAILED_NOLOGIN(48001),
    USER_NOT_REGISTER(48002),
    NO_LOGIN_USER(48003),
    PASSWORD_ERROR(48004),
    LOGIN_OVER_NUMBER(48005),
    USER_REGISTERED(48006),
    LOGIN_MSG_CODE_ERROR(48007),
    SEND_MSG_CODE_OVER_NUMBER(9999),
    WECHAT_MP_ERROR(40001),
    USER_IN_BLACKLIST(50002);

    private int value;
    private String msg;

    private WebCodeEnum(int value) {
        this.value = value;
    }

    private WebCodeEnum(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return this.value;
    }

    public String getMsg() {
        return StringUtils.isEmpty(this.msg) ? "" : this.msg;
    }
}

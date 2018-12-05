//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.demo.enums;


import org.springframework.util.StringUtils;

public enum WebCodeEnum {
    ERROR(-1),
    SUCCESS(0),
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
    USER_IN_BLACKLIST(50002),
    CLASSIN_GET_VIDEO_ERROR(60001),
    TEACHER_GET_CONFLICT_ERROR(80001, "老师资源冲突，请确认后再重新提交"),
    ADD_GROUP_CLASS_FAILED(80002, "添加孩子失败"),
    KID_GET_CONFLICT_ERROR(80003, "孩子已在其他班级中，不可重复添加"),
    ORDER_OVERDUED(70001),
    ORDER_HAS_PAIED(70002),
    COURSE_FOLDER_ID_EXIST(90001, "classin课程文件夹已存在"),
    PARAMS_FORMMART_ERROR(999999);

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

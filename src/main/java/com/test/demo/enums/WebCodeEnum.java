//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.demo.enums;


import org.springframework.util.StringUtils;

public enum WebCodeEnum {
    ERROR(-1),
    SUCCESS(0);

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

package com.spyker.application.enums;

import com.spyker.framework.response.RestCode;

/**
 *
 */
public enum SuccessCode implements RestCode {

    OK(200, "OK");

    public final int code;
    public final String msg;

    SuccessCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
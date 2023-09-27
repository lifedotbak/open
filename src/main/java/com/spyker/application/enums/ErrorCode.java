package com.spyker.application.enums;

import com.spyker.framework.response.RestCode;

/**
 * 业务异常代码
 */
public enum ErrorCode implements RestCode {

    UNKNOWN_ERROR(1, "未知异常");

    public final int code;
    public final String msg;

    ErrorCode(int code, String msg) {
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
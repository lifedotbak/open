package com.spyker.commons.enums;

import com.spyker.framework.web.response.IResponseCode;

import lombok.Getter;

/** 业务异常代码 */
@Getter
public enum BusinessErrorCodeEnum implements IResponseCode {
    UNKNOWN_ERROR(-1, "未知异常");

    private final int code;
    private final String message;

    BusinessErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
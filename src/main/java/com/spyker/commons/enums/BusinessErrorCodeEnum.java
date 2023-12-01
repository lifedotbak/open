package com.spyker.commons.enums;

import com.spyker.framework.response.IResponseCode;
import lombok.Getter;

/**
 * 业务异常代码
 */
@Getter
public enum BusinessErrorCodeEnum implements IResponseCode {

    UNKNOWN_ERROR(-1, "未知异常");

    private int code;
    private String message;

    BusinessErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
package com.spyker.base.enums;

import com.spyker.framework.response.ResponseErrorCode;
import lombok.Getter;

/**
 * 业务异常代码
 */
@Getter
public enum ErrorCode implements ResponseErrorCode {

    UNKNOWN_ERROR(-1, "未知异常");

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
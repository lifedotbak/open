package com.spyker.framework.exception;

import cn.dev33.satoken.exception.SaTokenException;
import com.spyker.framework.util.MessageUtils;
import com.spyker.framework.util.StringUtils;

/**
 * 全局异常
 *
 * @author spyker
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String code, Object[] args) {

        this.code = code;
        this.args = args;
    }

    public GlobalException(Throwable cause) {
        super(cause);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return this.code;
    }

    public GlobalException setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(code)) {
            message = MessageUtils.message(code, args);
        }
        if (message == null) {

        }
        return message;
    }

}
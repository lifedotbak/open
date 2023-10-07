package com.spyker.framework.exception.user;

import com.spyker.framework.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author platform
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
package com.spyker.framework.exception;

import cn.dev33.satoken.exception.SaTokenException;
import com.spyker.framework.response.RestResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public RestResponse handlerException(Exception e) {

        if (e instanceof SaTokenException) {
            return RestResponse.error(-1, "权限认证失败");
        } else {
            return RestResponse.error(-1, "系统异常");
        }

    }
}
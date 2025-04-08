package com.spyker.framework.exception.handler.impl;

import com.spyker.framework.exception.entity.ExceptionLog;
import com.spyker.framework.exception.handler.ExceptionLogHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultExceptionLogHandler implements ExceptionLogHandler {

    @Override
    public void handler(ExceptionLog exceptionLog) {

        log.error("defaultExceptionLogHandler -->{}", exceptionLog);
    }
}

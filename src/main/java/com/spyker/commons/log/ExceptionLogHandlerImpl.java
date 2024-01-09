package com.spyker.commons.log;

import com.spyker.framework.exception.entity.ExceptionLog;
import com.spyker.framework.exception.handler.ExceptionLogHandler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExceptionLogHandlerImpl implements ExceptionLogHandler {

    @Override
    public void handler(ExceptionLog exceptionLog) {

        log.error("exceptionLogHandlerImpl -->{}", exceptionLog);
    }
}
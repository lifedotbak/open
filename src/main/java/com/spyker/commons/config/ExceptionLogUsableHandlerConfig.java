package com.spyker.commons.config;

import com.spyker.commons.log.ExceptionLogHandlerImpl;
import com.spyker.framework.exception.handler.ExceptionLogUsableHandler;

import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Configuration;

/** 启用此注解，DbLogServiceImpl会覆盖DefaultLogServiceImpl日志处理具体方法 */
@Configuration
@RequiredArgsConstructor
public class ExceptionLogUsableHandlerConfig {

    private final ExceptionLogUsableHandler exceptionLogUsableHandler;

    private final ExceptionLogHandlerImpl dbLogServiceImpl;

    @PostConstruct
    public void addChian() {

        exceptionLogUsableHandler.initHandler(dbLogServiceImpl);
    }
}
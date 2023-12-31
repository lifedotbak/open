package com.spyker.framework.exception.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spyker.framework.exception.handler.ExceptionLogUsableHandler;
import com.spyker.framework.exception.handler.impl.DefaultExceptionLogHandler;

@Configuration
public class ExceptionLogHandlerConfig {

    @Autowired private DefaultExceptionLogHandler defaultLogServiceImpl;

    @Bean
    public ExceptionLogUsableHandler initExceptionChain() {

        ExceptionLogUsableHandler exceptionChain = new ExceptionLogUsableHandler();

        exceptionChain.initHandler(defaultLogServiceImpl);

        // exceptionChain.addChian(consoleLogService);

        return exceptionChain;
    }
}
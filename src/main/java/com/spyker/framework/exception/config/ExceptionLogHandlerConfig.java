package com.spyker.framework.exception.config;

import com.spyker.framework.exception.handler.ExceptionLogUsableHandler;
import com.spyker.framework.exception.handler.impl.DefaultExceptionLogHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionLogHandlerConfig {

    @Bean
    public ExceptionLogUsableHandler initExceptionChain(
            DefaultExceptionLogHandler defaultLogServiceImpl) {

        ExceptionLogUsableHandler exceptionChain = new ExceptionLogUsableHandler();

        exceptionChain.initHandler(defaultLogServiceImpl);

        // exceptionChain.addChian(consoleLogService);

        return exceptionChain;
    }
}
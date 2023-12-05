package com.spyker.commons.config;

import com.spyker.commons.log.ExceptionLogHandlerImpl;
import com.spyker.framework.exception.handler.ExceptionLogUsableHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 启用此注解，DbLogServiceImpl会覆盖DefaultLogServiceImpl日志处理具体方法
 */
@Configuration
public class ExceptionLogUsableHandlerConfig {

    @Autowired
    private ExceptionLogUsableHandler exceptionLogUsableHandler;

    @Autowired
    private ExceptionLogHandlerImpl dbLogServiceImpl;

    @Bean
    public void addChian() {

        exceptionLogUsableHandler.initHandler(dbLogServiceImpl);
    }
}
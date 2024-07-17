package com.spyker.framework.exception.config;

import com.spyker.framework.exception.handler.ExceptionLogHandler;
import com.spyker.framework.exception.handler.impl.DefaultExceptionLogHandler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(ExceptionLogHandler.class)
public class ExceptionLogHandlerConfig {

    /**
     * 默认异常日志处理器
     *
     * @return
     */
    @Bean
    public ExceptionLogHandler defaultExceptionLogHandler() {

        return new DefaultExceptionLogHandler();
    }
}
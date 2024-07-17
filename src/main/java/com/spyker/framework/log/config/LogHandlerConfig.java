package com.spyker.framework.log.config;

import com.spyker.framework.log.handler.LogHandler;
import com.spyker.framework.log.handler.impl.DefaultLogHandler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(LogHandler.class)
public class LogHandlerConfig {

    /**
     * 默认日志处理器
     *
     * @return
     */
    @Bean
    public LogHandler defaultExceptionLogHandler() {

        return new DefaultLogHandler();
    }
}
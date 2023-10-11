package com.spyker.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * springboot项目需要开启此配置，springcloud项目无需此类，springcloud默认开启
 *
 * @author spyker
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    private RestTemplateBuilder builder;

    // 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
    @Bean
    public RestTemplate restTemplate() {

        Duration readTime = Duration.ofMinutes(1);
        Duration connectTime = Duration.ofMinutes(1);

        return builder.setReadTimeout(readTime).setConnectTimeout(connectTime).build();
    }
}
package com.spyker.framework.zlmediakit.config;

import io.github.lunasaw.zlm.hook.service.ZlmHookService;
import io.github.lunasaw.zlm.hook.service.impl.DefaultZlmHookServiceImpl;
import io.github.lunasaw.zlm.node.LoadBalancer;
import io.github.lunasaw.zlm.node.impl.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/** 解决zlm-spring-boot-starter(1.0.3)当前springboot(2.7.18)版本于系统springboot版本(3.2.4)不兼容的问题 */
@AutoConfiguration
@EnableConfigurationProperties(ExZlmProperties.class)
@ComponentScan(basePackages = "io.github.lunasaw.zlm")
@ConditionalOnProperty(prefix = "zlm", name = "enable", havingValue = "true")
@Slf4j
public class ExZlmAutoConfiguration {

    @Autowired ExZlmProperties exZlmProperties;

    @Bean
    @ConditionalOnMissingBean
    public LoadBalancer loadBalancer() {

        log.info("loadBalancer init");

        switch (exZlmProperties.getBalance()) {
            case RANDOM:
                return new RandomLoadBalancer();
            case ROUND_ROBIN:
                return new RoundRobinLoadBalancer();
            case CONSISTENT_HASHING:
                return new ConsistentHashingLoadBalander();
            case WEIGHT_RANDOM:
                return new WeightRandomLoadBalancer();
            case WEIGHT_ROUND_ROBIN:
                return new WeightRoundRobinLoadBalancer();
            default:
                throw new RuntimeException("未找到负载均衡器");
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public ZlmHookService zlmHookService() {

        log.info("zlmHookService init");

        return new DefaultZlmHookServiceImpl();
    }
}

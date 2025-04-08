package com.spyker.framework.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(value = "transactional", ignoreInvalidFields = true)
public class GlobalTransactionalConfigProperties {

    private String pointcut;
    private List<String> requiredMethods;
    private List<String> readOnlyMethods;
}

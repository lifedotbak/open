package com.spyker.framework.third.xxxjob.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * xxljob配置类
 *
 * @author Lion Li
 */
@Data
@Component
@ConfigurationProperties(prefix = "xxl-server")
@ConditionalOnProperty(prefix = "xxl-server", name = "enabled", havingValue = "true")
public class XxlServerProperties {

    private String url;
    private String userName;
    private String password;

    private int jobGroup;
    private String author;
    private String jobDesc;
    private String glueType;
    private String executorRouteStrategy;
    private String misfireStrategy;
    private String executorBlockStrategy;
}
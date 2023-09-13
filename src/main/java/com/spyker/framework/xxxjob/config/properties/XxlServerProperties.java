package com.spyker.framework.xxxjob.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * xxljob配置类
 *
 * @author Lion Li
 */
@Data
@ConfigurationProperties(prefix = "xxl-server")
@Component
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
package com.application.gateway.config;

import com.alibaba.nacos.client.naming.event.InstancesChangeEvent;
import com.alibaba.nacos.common.notify.Event;
import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.common.notify.listener.Subscriber;
import com.application.common.core.utils.StringUtils;

import org.springdoc.core.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * SpringDoc配置类
 *
 * @author ruoyi
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(value = "springdoc.api-docs.enabled", matchIfMissing = true)
public class SpringDocConfig implements InitializingBean {
    @Autowired private SwaggerUiConfigProperties swaggerUiConfigProperties;

    @Autowired private DiscoveryClient discoveryClient;

    /** 在初始化后调用的方法 */
    @Override
    public void afterPropertiesSet() {
        NotifyCenter.registerSubscriber(
                new SwaggerDocRegister(swaggerUiConfigProperties, discoveryClient));
    }
}

/** Swagger文档注册器 */
class SwaggerDocRegister extends Subscriber<InstancesChangeEvent> {
    private static final String[] EXCLUDE_ROUTES =
            new String[] {
                "application-gateway", "application-auth", "application-file", "application-monitor"
            };
    private SwaggerUiConfigProperties swaggerUiConfigProperties;
    private DiscoveryClient discoveryClient;

    public SwaggerDocRegister(
            SwaggerUiConfigProperties swaggerUiConfigProperties, DiscoveryClient discoveryClient) {
        this.swaggerUiConfigProperties = swaggerUiConfigProperties;
        this.discoveryClient = discoveryClient;
    }

    /**
     * 事件回调方法，处理InstancesChangeEvent事件
     *
     * @param event 事件对象
     */
    @Override
    public void onEvent(InstancesChangeEvent event) {
        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> swaggerUrlSet =
                discoveryClient.getServices().stream()
                        .flatMap(serviceId -> discoveryClient.getInstances(serviceId).stream())
                        .filter(
                                instance ->
                                        !StringUtils.equalsAnyIgnoreCase(
                                                instance.getServiceId(), EXCLUDE_ROUTES))
                        .map(
                                instance -> {
                                    AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl =
                                            new AbstractSwaggerUiConfigProperties.SwaggerUrl();
                                    swaggerUrl.setName(instance.getServiceId());
                                    swaggerUrl.setUrl(
                                            String.format(
                                                    "/%s/v3/api-docs", instance.getServiceId()));
                                    return swaggerUrl;
                                })
                        .collect(Collectors.toSet());

        swaggerUiConfigProperties.setUrls(swaggerUrlSet);
    }

    /**
     * 订阅类型方法，返回订阅的事件类型
     *
     * @return 订阅的事件类型
     */
    @Override
    public Class<? extends Event> subscribeType() {
        return InstancesChangeEvent.class;
    }
}
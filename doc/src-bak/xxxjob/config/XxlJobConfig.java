package com.spyker.framework.xxxjob.config;

import cn.hutool.core.collection.CollUtil;

import com.spyker.framework.util.StreamUtils;
import com.spyker.framework.xxxjob.properties.XxlJobProperties;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * xxl-job config 依赖spring-cloud-commons包
 *
 * @author Lion Li
 */
@Slf4j
@AutoConfiguration
@ConditionalOnClass(XxlJobProperties.class)
@AllArgsConstructor
public class XxlJobConfig {

    private final XxlJobProperties xxlJobProperties;

    private final DiscoveryClient discoveryClient;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(
                "=======================================================================================================================");
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        if (StringUtils.isNotBlank(xxlJobProperties.getAdminAppname())) {
            List<ServiceInstance> instances =
                    discoveryClient.getInstances(xxlJobProperties.getAdminAppname());

            log.info(">>>>>>>>>>> instances---->{}", instances);

            if (CollUtil.isEmpty(instances)) {
                throw new RuntimeException("调度中心不存在!");
            }
            String serverList =
                    StreamUtils.join(
                            instances,
                            instance ->
                                    String.format(
                                            "http://%s:%s",
                                            instance.getHost(), instance.getPort()));

            log.info(">>>>>>>>>>> serverList---->{}", serverList);

            xxlJobSpringExecutor.setAdminAddresses(serverList);
        } else {
            xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdminAddresses());
        }
        xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getAccessToken());
        XxlJobProperties.Executor executor = xxlJobProperties.getExecutor();
        xxlJobSpringExecutor.setAppname(executor.getAppname());
        xxlJobSpringExecutor.setAddress(executor.getAddress());
        xxlJobSpringExecutor.setIp(executor.getIp());
        xxlJobSpringExecutor.setPort(executor.getPort());
        xxlJobSpringExecutor.setLogPath(executor.getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(executor.getLogRetentionDays());
        return xxlJobSpringExecutor;
    }
}
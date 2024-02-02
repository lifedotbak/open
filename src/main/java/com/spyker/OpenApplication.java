package com.spyker;

import lombok.extern.slf4j.Slf4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 默认swagger访问地址 http://127.0.0.1:8080/open/swagger-ui/index.html
 *
 * <p>knife4j优化后访问地址 http://127.0.0.1:8080/open/doc.html
 *
 * <p>EnableRetry--->springboot 重试机制
 */
/**
 * 配置 ID，采用类似 package.class（如com.taobao.tc.refund.log.level）的命名规则保证全局唯一性，class
 * 部分建议是配置的业务含义。全部字符小写。只允许英文字符和 4 种特殊字符（”.”、”:”、”-”、”_”），不超过 256 字节。
 */
// @NacosPropertySource(dataId = "open", groupId = "dev", autoRefreshed = true, type =
// ConfigType.YAML)
@SpringBootApplication
@MapperScan({"com.spyker.*.mapper"})
@EnableScheduling
@EnableAsync
@EnableCaching
@EnableRetry
// @EnableConfigurationProperties
// @EnableAdminServer
@Slf4j
public class OpenApplication {

    public static void main(String[] args) {

        SpringApplication.run(OpenApplication.class, args);
    }
}
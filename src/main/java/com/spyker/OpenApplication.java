package com.spyker;

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
 * <p>集成camunda后，camunda管理平台访问地址-->http://127.0.0.1:8080/open
 *
 * <p>camunda管理平台rest地址-->http://127.0.0.1:8080/open/rest
 *
 * <p>knife4j优化后访问地址 http://127.0.0.1:8080/open/doc.html
 *
 * <p>EnableRetry--->springboot 重试机制
 */
@SpringBootApplication
@MapperScan({"com.spyker.*.mapper", "com.spyker.flowable.biz.mapper"})
@EnableScheduling
@EnableAsync
@EnableCaching
@EnableRetry
// @ComponentScan(value = {"io.github.lunasaw.zlm"})
// @EnableAdminServer
public class OpenApplication {

    public static void main(String[] args) {

        SpringApplication.run(OpenApplication.class, args);
    }
}
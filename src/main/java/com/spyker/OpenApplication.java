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
@SpringBootApplication
@MapperScan({"com.spyker.*.mapper"})
@EnableScheduling
@EnableAsync
@EnableCaching
@EnableRetry
// @EnableAdminServer
@Slf4j
public class OpenApplication {

    public static void main(String[] args) {

        SpringApplication.run(OpenApplication.class, args);
    }
}
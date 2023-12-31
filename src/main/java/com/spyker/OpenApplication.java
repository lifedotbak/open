package com.spyker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 默认swagger访问地址 http://127.0.0.1:8080/open/swagger-ui/index.html
 *
 * <p>knife4j优化后访问地址 http://127.0.0.1:8080/open/doc.html
 */
@SpringBootApplication
@MapperScan({"com.spyker.*.mapper"})
@EnableScheduling
@EnableAsync
@EnableCaching
// @EnableAdminServer
public class OpenApplication {

    public static void main(String[] args) {

        SpringApplication.run(OpenApplication.class, args);
    }
}
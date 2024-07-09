package cc.flyflow.web;

import lombok.extern.slf4j.Slf4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@MapperScan(basePackages = "cc.flyflow.biz.mapper")
@SpringBootApplication(scanBasePackages = {"cc.flyflow"})
@EnableCaching
public class WebApp {
    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
        log.info("=====================Web App  Start========================");
    }
}
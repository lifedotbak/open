package com.spyker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * http://127.0.0.1:8080/open/swagger-ui/index.html
 */
@SpringBootApplication
@MapperScan({ "com.spyker.application.mapper" })
@EnableScheduling
@EnableAsync
public class OpenApplication {

	public static void main(String[] args) {

		SpringApplication.run(OpenApplication.class, args);
	}

}
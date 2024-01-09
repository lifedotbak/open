package com.spyker.framework.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name}")
    private String application;

    @Bean
    public OpenAPI springOpenAPI() {

        OpenAPI openApi = new OpenAPI();

        openApi.info(apiInfo());

        return openApi;
    }

    private Info apiInfo() {

        return new Info().title(application).description(application).version("V-0.0.1");
    }
}
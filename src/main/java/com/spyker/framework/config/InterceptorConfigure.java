package com.spyker.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfigure implements WebMvcConfigurer {

    public static final String[] EXCLUDE_PATHS = {
        "/**/*.gif", "/**/*.jpg", "/**/*.jpeg", "/**/*.png", "/**/*.css", "/**/*.js"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {}
}

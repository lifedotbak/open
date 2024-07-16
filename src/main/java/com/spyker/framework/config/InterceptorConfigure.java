package com.spyker.framework.config;

import com.spyker.framework.interceptor.IpInterceptor;
import com.spyker.framework.interceptor.ResultInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfigure implements WebMvcConfigurer {

    public static final String[] EXCLUDE_PATHS = {
        "/**/*.gif", "/**/*.jpg", "/**/*.jpeg", "/**/*.png", "/**/*.css", "/**/*.js"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new IpInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
        registry.addInterceptor(new ResultInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }
}
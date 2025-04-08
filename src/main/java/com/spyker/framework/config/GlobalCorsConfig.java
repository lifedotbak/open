package com.spyker.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 处理ajax跨域请求（开发时使用）
 *
 * <p>GlobalCorsConfig
 */
// 如果使用网关服务调用微服务，则注释掉Configuration注解；如果不使用网关服务，则需将Configuration注解的注释放开
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许任何域名访问
        corsConfiguration.addAllowedOrigin("*");
        // 允许任何header访问
        corsConfiguration.addAllowedHeader("*");
        // 允许任何方法访问
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }
}

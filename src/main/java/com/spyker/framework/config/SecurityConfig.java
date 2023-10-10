package com.spyker.framework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.stereotype.Component;

/**
 * spring security配置
 *
 * @author platform
 */
@RequiredArgsConstructor
@Component
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义RememberMe服务token持久化仓库
     */
    @Bean
    public InMemoryTokenRepositoryImpl inMemoryTokenRepositoryImpl() {

        return new InMemoryTokenRepositoryImpl();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return authenticationManager;
    }

}
package com.spyker.framework.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;

@Configuration
public class JedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Value("${spring.data.redis.timeout}")
    private int timeout;

    @Value("${spring.data.redis.password}")
    private String password;

    @Bean
    public GenericObjectPoolConfig genericObjectPoolConfig() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        /** jmxEnable=false解决mxbean重名问题 */
        genericObjectPoolConfig.setJmxEnabled(false);
        return genericObjectPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(GenericObjectPoolConfig genericObjectPoolConfig) {

        JedisPool jedisPool = new JedisPool(genericObjectPoolConfig, host, port, timeout, password);
        return jedisPool;
    }
}
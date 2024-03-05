package com.spyker.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
/**
 * 通过与 Redis Repository 合作，你可以在 Redis Hashe 中无缝转换和存储domain对象，应用自定义映射策略，并使用二级索引 Redis Repository 至少需要
 * Redis Server 2.8.0 版本，并且不能使用事务。请确保使用 禁用事务支持 的 RedisTemplate。
 */
@EnableRedisRepositories
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());

        redisTemplate.setEnableTransactionSupport(false);

        return redisTemplate;
    }
}
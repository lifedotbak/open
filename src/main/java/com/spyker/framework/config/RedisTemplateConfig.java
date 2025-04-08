package com.spyker.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.data.redis.support.collections.RedisList;

@Configuration
/**
 * 通过与 Redis Repository 合作，你可以在 Redis Hashe 中无缝转换和存储domain对象，应用自定义映射策略，并使用二级索引 Redis Repository 至少需要
 * Redis Server 2.8.0 版本，并且不能使用事务。请确保使用 禁用事务支持 的 RedisTemplate。
 */
@EnableRedisRepositories
public class RedisTemplateConfig {

    /**
     * RedisList 在 Redis 之上实现了 List、Queue 和 Deque 合约（以及它们的等效阻塞同类），以最小的配置将存储暴露为
     * FIFO（先入先出）、LIFO（后入先出）或上限集合。
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public RedisList<Object> redisListTemplate(RedisTemplate<String, Object> redisTemplate) {

        /** 在代码中，@Autowired Deque<Object> deque; 这样使用 */
        return new DefaultRedisList<>("app-queue", redisTemplate);
    }

    /**
     * RedisTemplate 线程安全
     *
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisObjectKeyTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());

        redisTemplate.setEnableTransactionSupport(false);

        return redisTemplate;
    }

    /**
     * RedisTemplate 线程安全
     *
     * @param factory
     * @return
     */
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

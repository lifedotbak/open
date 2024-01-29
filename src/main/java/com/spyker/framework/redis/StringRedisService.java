package com.spyker.framework.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
// @ConditionalOnProperty(prefix = "spring.data.redis", name = "enabled", havingValue = "true")
@Slf4j
@RequiredArgsConstructor
public class StringRedisService {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * @param key
     * @param value
     * @return
     */
    public Boolean set(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 普通缓存放入
     *
     * @param key 键
     * @param value 值
     * @param expireTime 超时时间(秒)
     * @return true成功 false失败
     */
    public void set(String key, String value, int expireTime) {

        stringRedisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        return key == null ? null : stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 在原有的值基础上新增字符串到末尾。
     *
     * @param key 键
     * @param value 追加的值
     * @return true成功 false失败
     */
    public void append(String key, String value) {
        stringRedisTemplate.opsForValue().append(key, value);
    }

    /**
     * 截取key键对应值得字符串，从开始下标位置开始到结束下标的位置(包含结束下标)的字符串。
     *
     * @param key 键
     * @param start 开始位置
     * @param end 结束位置
     * @return
     */
    public String get(String key, long start, long end) {
        return stringRedisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * 获取原来key键对应的值并重新赋新值。
     *
     * @param key 键
     * @param value 值
     * @return 原来旧值
     */
    public Object getAndSet(String key, String value) {
        return stringRedisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 获取指定字符串的长度。
     *
     * @param key
     * @return 长度
     */
    public long size(String key) {
        return stringRedisTemplate.opsForValue().size(key);
    }

    /**
     * 如果键不存在则新增,存在则不改变已经有的值
     *
     * @param key
     * @param value
     * @return true成功 false失败
     */
    public boolean setIfAbsent(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 递增
     *
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return 返回增加后的值
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("递增因子必须大于0");
        }
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return 返回减少后的值
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("递减因子必须大于0");
        }
        return stringRedisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 设置map集合到redis。
     *
     * @param map
     */
    public void multiSet(Map<String, String> map) {
        stringRedisTemplate.opsForValue().multiSet(map);
    }

    /**
     * 根据集合取出对应的value值。
     *
     * @param list
     * @return
     */
    public List<String> multiGet(List<String> list) {
        return stringRedisTemplate.opsForValue().multiGet(list);
    }
}
package com.spyker.framework.oss.factory;

import com.spyker.framework.oss.constant.OssConstant;
import com.spyker.framework.oss.core.OssClient;
import com.spyker.framework.oss.exception.OssException;
import com.spyker.framework.oss.properties.OssProperties;
import com.spyker.framework.redis.RedissonService;
import com.spyker.framework.util.CacheUtils;
import com.spyker.framework.util.ExJsonUtils;
import com.spyker.framework.util.ExStringUtils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Configuration
public class OssFactory {

    @Autowired private RedissonService redissonService;

    private static final Map<String, OssClient> CLIENT_CACHE = new ConcurrentHashMap<>();

    /** 初始化工厂 */
    public void init() {
        log.info("初始化OSS工厂");
        redissonService.subscribe(
                OssConstant.DEFAULT_CONFIG_KEY,
                String.class,
                configKey -> {
                    OssClient client = getClient(configKey);
                    // 如果client为空则初始化
                    if (client != null) {
                        refresh(configKey);
                        log.info("订阅刷新OSS配置 => " + configKey);
                    } else {
                        log.info("初始化OSS配置 => " + configKey);
                        instance();
                    }
                });
    }

    /** 获取默认实例 */
    public OssClient instance() {
        // 获取redis 默认类型
        String configKey = redissonService.getCacheObject(OssConstant.DEFAULT_CONFIG_KEY);
        if (ExStringUtils.isEmpty(configKey)) {
            throw new OssException("文件存储服务类型无法找到!");
        }
        return instance(configKey);
    }

    /** 根据类型获取实例 */
    public OssClient instance(String configKey) {
        OssClient client = getClient(configKey);
        if (client == null) {
            refresh(configKey);
            return getClient(configKey);
        }
        return client;
    }

    private void refresh(String configKey) {
        String json = CacheUtils.get(OssConstant.SYS_OSS_CONFIG_KEY, configKey);
        if (json == null) {
            throw new OssException("系统异常, '" + configKey + "'配置信息不存在!");
        }
        OssProperties properties = ExJsonUtils.parseObject(json, OssProperties.class);
        CLIENT_CACHE.put(configKey, new OssClient(configKey, properties));
    }

    private OssClient getClient(String configKey) {
        return CLIENT_CACHE.get(configKey);
    }
}
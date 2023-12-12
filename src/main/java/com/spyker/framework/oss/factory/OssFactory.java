package com.spyker.framework.oss.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.spyker.framework.oss.constant.CacheNames;
import com.spyker.framework.oss.constant.OssConstant;
import com.spyker.framework.oss.core.OssClient;
import com.spyker.framework.oss.exception.OssException;
import com.spyker.framework.oss.properties.OssProperties;
import com.spyker.framework.util.CacheUtils;
import com.spyker.framework.util.ExJsonUtils;
import com.spyker.framework.util.ExStringUtils;
import com.spyker.framework.util.RedisUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OssFactory {

	private static final Map<String, OssClient> CLIENT_CACHE = new ConcurrentHashMap<>();

	/**
	 * 初始化工厂
	 */
	public static void init() {
		log.info("初始化OSS工厂");
		RedisUtils.subscribe(OssConstant.DEFAULT_CONFIG_KEY, String.class, configKey -> {
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

	/**
	 * 获取默认实例
	 */
	public static OssClient instance() {
		// 获取redis 默认类型
		String configKey = RedisUtils.getCacheObject(OssConstant.DEFAULT_CONFIG_KEY);
		if (ExStringUtils.isEmpty(configKey)) {
			throw new OssException("文件存储服务类型无法找到!");
		}
		return instance(configKey);
	}

	/**
	 * 根据类型获取实例
	 */
	public static OssClient instance(String configKey) {
		OssClient client = getClient(configKey);
		if (client == null) {
			refresh(configKey);
			return getClient(configKey);
		}
		return client;
	}

	private static void refresh(String configKey) {
		String json = CacheUtils.get(CacheNames.SYS_OSS_CONFIG, configKey);
		if (json == null) {
			throw new OssException("系统异常, '" + configKey + "'配置信息不存在!");
		}
		OssProperties properties = ExJsonUtils.parseObject(json, OssProperties.class);
		CLIENT_CACHE.put(configKey, new OssClient(configKey, properties));
	}

	private static OssClient getClient(String configKey) {
		return CLIENT_CACHE.get(configKey);
	}

}

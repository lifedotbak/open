package com.spyker.iot.vmp.service.impl;

import com.spyker.iot.vmp.service.IUserApiKeyService;
import com.spyker.iot.vmp.storager.dao.UserApiKeyMapper;
import com.spyker.iot.vmp.storager.dao.dto.UserApiKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserApiKeyServiceImpl implements IUserApiKeyService {

    @Autowired UserApiKeyMapper userApiKeyMapper;

    @Autowired private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public int addApiKey(UserApiKey userApiKey) {
        return userApiKeyMapper.add(userApiKey);
    }

    @Override
    public boolean isApiKeyExists(String apiKey) {
        return userApiKeyMapper.isApiKeyExists(apiKey);
    }

    @Cacheable(cacheNames = "userApiKey", key = "#id", sync = true)
    @Override
    public UserApiKey getUserApiKeyById(Integer id) {
        return userApiKeyMapper.selectById(id);
    }

    @CacheEvict(cacheNames = "userApiKey", key = "#id")
    @Override
    public int enable(Integer id) {
        return userApiKeyMapper.enable(id);
    }

    @CacheEvict(cacheNames = "userApiKey", key = "#id")
    @Override
    public int disable(Integer id) {
        return userApiKeyMapper.disable(id);
    }

    @CacheEvict(cacheNames = "userApiKey", key = "#id")
    @Override
    public int remark(Integer id, String remark) {
        return userApiKeyMapper.remark(id, remark);
    }

    @CacheEvict(cacheNames = "userApiKey", key = "#id")
    @Override
    public int delete(Integer id) {
        return userApiKeyMapper.delete(id);
    }

    @CacheEvict(cacheNames = "userApiKey", key = "#id")
    @Override
    public int reset(Integer id, String apiKey) {
        return userApiKeyMapper.apiKey(id, apiKey);
    }
}
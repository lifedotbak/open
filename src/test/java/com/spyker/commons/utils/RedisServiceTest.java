package com.spyker.commons.utils;

import com.spyker.BaseTest;
import com.spyker.framework.redis.RedisService;
import com.spyker.framework.redis.StringRedisService;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RedisServiceTest extends BaseTest {

    @Autowired RedisService redisService;
    @Autowired StringRedisService stringRedisService;

    @Test
    public void test() {

        Map<String, String> sss = new HashMap<>();

        sss.put("a", "b");

        redisService.setCacheObject("Key1", sss);

        Map<String, String> vvv = redisService.getCacheObject("Key1");

        log.info("--->{}", vvv);

        stringRedisService.set("Key2", "y");
    }
}
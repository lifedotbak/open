package com.spyker.commons.utils;

import com.spyker.BaseTest;
import com.spyker.framework.redis.RedissonService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedissonServiceTest extends BaseTest {

    @Autowired private RedissonService redissonService;

    @Test
    public void test() {
        redissonService.setCacheObject("xxxx", "yyyyy");
    }
}
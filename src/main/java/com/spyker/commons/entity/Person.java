package com.spyker.commons.entity;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/** Spring Data Redis 实现 domain 实体 */
@RedisHash
@Data
public class Person {
    @Id String id;
    String firstname;
    String lastname;
}
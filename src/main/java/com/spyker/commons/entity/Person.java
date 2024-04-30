package com.spyker.commons.entity;

import lombok.Data;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/** Spring Data Redis 实现 domain 实体 */
@RedisHash
@Data
@ToString
public class Person {

    @Id String id;

    /** indexed 二级索引 */
    @Indexed String firstname;

    String lastname;
}
package com.spyker.framework.ratelimiter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 限流注解 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiting {

    // 默认每秒放入桶中的token
    double limitNum() default 20;

    String name() default "";
}
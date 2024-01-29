package com.spyker.framework.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/** 采用guava，令牌桶算法实现 */
@Aspect
@Component
@Slf4j
public class RateLimitAspect {

    private final ConcurrentHashMap<String, RateLimiter> rateLimiterConcurrentHashMap =
            new ConcurrentHashMap<>();

    //    @Pointcut("@annotation(com.spyker.framework.ratelimiter.Limiting)")
    //    public void serviceLimit() {
    //    }

    //    @Around("serviceLimit()")
    @Around("@annotation(Limiting)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 获取拦截的方法名
        Signature sig = point.getSignature();
        // 获取拦截的方法名
        MethodSignature msig = (MethodSignature) sig;
        // 返回被织入增加处理目标对象
        Object target = point.getTarget();
        // 为了获取注解信息
        Method currentMethod =
                target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        // 获取注解信息
        Limiting annotation = currentMethod.getAnnotation(Limiting.class);
        double limitNum = annotation.limitNum(); // 获取注解每秒加入桶中的token
        String functionName = msig.getName(); // 注解所在方法名区分不同的限流策略

        RateLimiter rateLimiter = null;
        if (rateLimiterConcurrentHashMap.containsKey(functionName)) {
            rateLimiter = rateLimiterConcurrentHashMap.get(functionName);
        } else {
            rateLimiterConcurrentHashMap.put(functionName, RateLimiter.create(limitNum));
            rateLimiter = rateLimiterConcurrentHashMap.get(functionName);
        }
        if (null != rateLimiter && rateLimiter.tryAcquire()) {
            log.info("处理完成");
            return point.proceed();
        } else {
            log.error("服务器繁忙，请稍后再试。");
            throw new RateLimitException("服务器繁忙，请稍后再试!");
        }
    }
}
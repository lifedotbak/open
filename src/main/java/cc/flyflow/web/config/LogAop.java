package cc.flyflow.web.config;

import cc.flyflow.common.utils.LogAopUtil;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LogAop {

    @Around("execution(* cc.flyflow.web.controller.*.*(..))")
    @SneakyThrows
    public Object writeLog(ProceedingJoinPoint point) {
        try {
            return LogAopUtil.write(point);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}
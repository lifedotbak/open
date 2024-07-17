package com.spyker.framework.log.aop;

import cn.hutool.core.util.StrUtil;

import com.flyflow.common.dto.R;
import com.flyflow.common.utils.JsonUtil;
import com.spyker.framework.log.annotation.NotWriteLogAnnotation;
import com.spyker.framework.util.IpUtils;
import com.yomahub.tlog.context.TLogContext;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/** 请求耗时报警时间 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    /** 排除敏感属性字段 */
    private static final String[] EXCLUDE_PROPERTIES = {
        "password", "oldPassword", "newPassword", "confirmPassword"
    };

    @Around("execution(* com.spyker.*.controller.*.*.*(..))")
    @SneakyThrows
    public Object writeLog(ProceedingJoinPoint point) {
        try {
            return write(point);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    /** 请求耗时报警时间 */
    @SneakyThrows
    private Object write(ProceedingJoinPoint point) {

        Object target = point.getTarget();

        String className = target.getClass().getName();
        String classSimpleName = target.getClass().getSimpleName();
        Object[] args = point.getArgs();

        Object proceed = null;
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        Method method = methodSignature.getMethod();
        NotWriteLogAnnotation notWriteLogAnno = method.getAnnotation(NotWriteLogAnnotation.class);
        if (notWriteLogAnno != null && notWriteLogAnno.exclude()) {

            return point.proceed(args);
        }
        if (StrUtil.equals(method.getName(), "error") || StrUtil.contains(className, "kotime")) {

            return point.proceed(args);
        }

        String ip = IpUtils.getIpAddr();

        log.info("ip --> {}", ip);

        String[] parameterNames = methodSignature.getParameterNames();
        Map<String, Object> paramMap = new HashMap<>();
        int length = parameterNames.length;
        for (int i = 0; i < length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        long l1 = System.currentTimeMillis();

        if (notWriteLogAnno != null && notWriteLogAnno.all()) {

        } else {
            if (notWriteLogAnno != null) {

                for (String p : EXCLUDE_PROPERTIES) {
                    paramMap.remove(p);
                }

                String[] paramsExclude = notWriteLogAnno.paramsExclude();
                for (String p : paramsExclude) {
                    paramMap.remove(p);
                }
            }
            log.info(
                    " 入参   类:  "
                            + className
                            + " 方法:  "
                            + method.getName()
                            + " 参数:  "
                            + JsonUtil.toJSONString(paramMap));
        }

        proceed = point.proceed(args);
        if (proceed instanceof R r) {
            r.setTraceId(TLogContext.getTraceId());
        }
        if (notWriteLogAnno != null && !notWriteLogAnno.printResultLog()) {
            return proceed;
        }

        long l2 = System.currentTimeMillis();

        log.info(
                "返回日志 类：{} 方法：{} 结果：{} 响应时间:{}",
                className,
                method.getName(),
                JsonUtil.toJSONString(proceed),
                l2 - l1);

        return proceed;
    }
}
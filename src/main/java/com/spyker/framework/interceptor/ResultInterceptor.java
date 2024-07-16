package com.spyker.framework.interceptor;

import com.spyker.framework.annotation.CustomResponseAnnotation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

@Component
@Slf4j
public class ResultInterceptor implements HandlerInterceptor {

    /* 使用自定义响应的标识 */
    private static final String CUSTOM_RESPONSE_RESULT_ANNOTATION =
            "CUSTOM-RESPONSE-RESULT-ANNOTATION";

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) {

        log.info("请求地址：{}", request.getRequestURI());

        // 正在处理请求的方法bean
        if (handler instanceof HandlerMethod handlerMethod) {
            // 获取当前类
            final Class<?> clazz = handlerMethod.getBeanType();
            // 获取当前方法
            final Method method = handlerMethod.getMethod();
            // 判断是否在类对象上加了注解
            if (clazz.isAnnotationPresent(CustomResponseAnnotation.class)) {
                // 设置该请求返回体，不需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(
                        CUSTOM_RESPONSE_RESULT_ANNOTATION,
                        clazz.getAnnotation(CustomResponseAnnotation.class));
            }
            // 判断是否在方法上加了注解
            else if (method.isAnnotationPresent(CustomResponseAnnotation.class)) {
                // 设置该请求返回体，不需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(
                        CUSTOM_RESPONSE_RESULT_ANNOTATION,
                        method.getAnnotation(CustomResponseAnnotation.class));
            }
        }
        return true;
    }
}
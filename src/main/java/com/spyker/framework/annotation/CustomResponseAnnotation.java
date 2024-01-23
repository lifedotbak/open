package com.spyker.framework.annotation;

import java.lang.annotation.*;

/**
 * @ClassName CustomResponseAnnotation @Description 自定义响应结果注解，由此注解表示返回值不经过统一封装返回
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD}) // 作用于方法和类（接口）上
@Documented
public @interface CustomResponseAnnotation {}
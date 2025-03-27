package com.spyker.framework.log.annotation;

import java.lang.annotation.*;

/**
 * 日志打印注解 添加到controller方法上
 *
 * @author 121232224@qq.com
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotWriteLogAnnotation {

    // 所有的字段都不记录日志
    boolean all() default true;

    boolean exclude() default false;

    // 排除记录日志的字段
    String[] paramsExclude() default {};

    /**
     * 是否打印返回值
     *
     * @return
     */
    boolean printResultLog() default true;
}
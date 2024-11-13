package com.spyker.framework.log.annotation;

import com.spyker.framework.constants.BusinessTypeEnum;
import com.spyker.framework.constants.OperatorTypeEnum;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 *
 * @author spyker
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLogAnnotation {

    /** 模块，支持%s等sting.format的占位符 */
    String title() default "";

    /**
     * 标题参数
     *
     * @return
     */
    String[] titleParamNames() default {};

    /** 功能 */
    BusinessTypeEnum businessType() default BusinessTypeEnum.OTHER;

    /** 操作人类别 */
    OperatorTypeEnum operatorType() default OperatorTypeEnum.MANAGE;

    /** 是否保存请求的参数 */
    boolean isSaveRequestData() default true;

    /** 是否保存响应的参数 */
    boolean isSaveResponseData() default true;

    /** 排除指定的请求参数 */
    String[] excludeParamNames() default {};
}
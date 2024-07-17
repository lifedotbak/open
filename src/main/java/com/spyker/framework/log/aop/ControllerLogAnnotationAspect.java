package com.spyker.framework.log.aop;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;

import com.alibaba.fastjson2.JSON;
import com.spyker.framework.constants.CommonsConstants;
import com.spyker.framework.filter.PropertyPreExcludeFilter;
import com.spyker.framework.log.annotation.ControllerLogAnnotation;
import com.spyker.framework.log.entity.OperationLog;
import com.spyker.framework.log.handler.LogHandler;
import com.spyker.framework.util.IpUtils;
import com.spyker.framework.util.http.ServletUtils;
import com.spyker.framework.util.text.ExStringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * 操作日志记录处理
 *
 * @author spyker
 */
@Aspect
@Component
@Slf4j
public class ControllerLogAnnotationAspect {

    /** 排除敏感属性字段 */
    public static final String[] EXCLUDE_PROPERTIES = {
        "password", "oldPassword", "newPassword", "confirmPassword"
    };

    /** 计算操作消耗时间 */
    private static final ThreadLocal<Long> TIME_THREADLOCAL =
            new NamedThreadLocal<Long>("Cost Time");

    @Autowired LogHandler logHandler;

    /** 处理请求前执行 */
    @Before(value = "@annotation(controllerLog)")
    public void boBefore(JoinPoint joinPoint, ControllerLogAnnotation controllerLog) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(
            JoinPoint joinPoint, ControllerLogAnnotation controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    protected void handleLog(
            final JoinPoint joinPoint,
            ControllerLogAnnotation controllerLog,
            final Exception e,
            Object jsonResult) {
        try {

            // 获取当前的用户
            String loginUserId = "";

            SaSession saSession = StpUtil.getSession();

            if (null != saSession) {
                Object userKey = saSession.get(CommonsConstants.LOGIN_USER_KEY);
                if (null != userKey) {
                    loginUserId = (String) userKey;
                }
            }

            // *========数据库日志=========*//
            OperationLog operLog = new OperationLog();
            operLog.setStatus(0);
            // 请求的地址
            String ip = IpUtils.getIpAddr();

            operLog.setOperName(loginUserId);
            operLog.setOperIp(ip);
            operLog.setOperUrl(
                    ExStringUtils.substring(ServletUtils.getRequest().getRequestURI(), 0, 255));

            if (e != null) {
                operLog.setStatus(1);
                operLog.setErrorMsg(ExStringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operLog, jsonResult);
            // 设置消耗时间
            operLog.setCostTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());

            operLog.setOperTime(new Date());

            log.info("controller op log=====>{}", operLog);

            logHandler.handler(operLog);

        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("异常信息:{}", exp.getMessage());
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log 日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(
            JoinPoint joinPoint,
            ControllerLogAnnotation log,
            OperationLog operLog,
            Object jsonResult)
            throws Exception {
        // 设置action动作
        operLog.setBusinessType(log.businessType().ordinal());
        // 设置标题
        operLog.setTitle(log.title());
        // 设置操作人类别
        operLog.setOperatorType(log.operatorType().ordinal());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operLog, log.excludeParamNames());
        }
        // 是否需要保存response，参数和值
        if (log.isSaveResponseData() && ExStringUtils.isNotNull(jsonResult)) {
            operLog.setJsonResult(ExStringUtils.substring(JSON.toJSONString(jsonResult), 0, 2000));
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(
            JoinPoint joinPoint, OperationLog operLog, String[] excludeParamNames)
            throws Exception {
        Map<?, ?> paramsMap = ServletUtils.getParamMap(ServletUtils.getRequest());
        String requestMethod = operLog.getRequestMethod();
        if (ExStringUtils.isEmpty(paramsMap)
                && (HttpMethod.PUT.name().equals(requestMethod)
                        || HttpMethod.POST.name().equals(requestMethod))) {
            String params = argsArrayToString(joinPoint.getArgs(), excludeParamNames);
            operLog.setOperParam(ExStringUtils.substring(params, 0, 2000));
        } else {
            operLog.setOperParam(
                    ExStringUtils.substring(
                            JSON.toJSONString(
                                    paramsMap, excludePropertyPreFilter(excludeParamNames)),
                            0,
                            2000));
        }
    }

    /** 参数拼装 */
    private String argsArrayToString(Object[] paramsArray, String[] excludeParamNames) {
        String params = "";
        if (paramsArray != null) {
            for (Object o : paramsArray) {
                if (ExStringUtils.isNotNull(o) && !isFilterObject(o)) {
                    try {
                        String jsonObj =
                                JSON.toJSONString(o, excludePropertyPreFilter(excludeParamNames));
                        params += jsonObj + " ";
                    } catch (Exception e) {
                    }
                }
            }
        }
        return params.trim();
    }

    /** 忽略敏感属性 */
    public PropertyPreExcludeFilter excludePropertyPreFilter(String[] excludeParamNames) {
        return new PropertyPreExcludeFilter()
                .addExcludes(ArrayUtils.addAll(EXCLUDE_PROPERTIES, excludeParamNames));
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else {
            if (Collection.class.isAssignableFrom(clazz)) {
                Collection collection = (Collection) o;
                for (Object value : collection) {
                    return value instanceof MultipartFile;
                }
            } else {
                if (Map.class.isAssignableFrom(clazz)) {
                    Map map = (Map) o;
                    for (Object value : map.entrySet()) {
                        Map.Entry entry = (Map.Entry) value;
                        return entry.getValue() instanceof MultipartFile;
                    }
                }
            }
        }
        return o instanceof MultipartFile
                || o instanceof HttpServletRequest
                || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(
            JoinPoint joinPoint, ControllerLogAnnotation controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }
}
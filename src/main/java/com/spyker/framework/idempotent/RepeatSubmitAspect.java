package com.spyker.framework.idempotent;

import cn.dev33.satoken.SaManager;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;

import com.spyker.framework.exception.BusinessException;
import com.spyker.framework.redis.RedisService;
import com.spyker.framework.util.ExJsonUtils;
import com.spyker.framework.util.MessageUtils;
import com.spyker.framework.util.http.ServletUtils;
import com.spyker.framework.web.response.RestResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/** 防止重复提交(参考美团GTIS防重系统) */
@Aspect
@Component
public class RepeatSubmitAspect {

    private static final ThreadLocal<String> KEY_CACHE = new ThreadLocal<>();

    private static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    @Autowired private RedisService redisService;

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(repeatSubmit)", returning = "jsonResult")
    public void doAfterReturning(
            JoinPoint joinPoint, RepeatSubmit repeatSubmit, Object jsonResult) {
        if (jsonResult instanceof RestResponse) {
            try {
                RestResponse<?> r = (RestResponse<?>) jsonResult;
                // 成功则不删除redis数据 保证在有效时间内无法重复提交
                if (r.getCode() == 200) {
                    return;
                }
                redisService.deleteObject(KEY_CACHE.get());
            } finally {
                KEY_CACHE.remove();
            }
        }
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "@annotation(repeatSubmit)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, RepeatSubmit repeatSubmit, Exception e) {
        redisService.deleteObject(KEY_CACHE.get());
        KEY_CACHE.remove();
    }

    @Before("@annotation(repeatSubmit)")
    public void doBefore(JoinPoint point, RepeatSubmit repeatSubmit) throws Throwable {
        // 如果注解不为0 则使用注解数值
        long interval = repeatSubmit.timeUnit().toMillis(repeatSubmit.interval());

        if (interval < 1000) {
            throw new BusinessException("重复提交间隔时间不能小于'1'秒");
        }

        HttpServletRequest request = ServletUtils.getRequest();
        String nowParams = argsArrayToString(point.getArgs());

        // 请求地址（作为存放cache的key值）
        String url = request.getRequestURI();

        // 唯一值（没有消息头则使用请求地址）
        String submitKey =
                StringUtils.trimToEmpty(request.getHeader(SaManager.getConfig().getTokenName()));

        submitKey = SecureUtil.md5(submitKey + ":" + nowParams);
        // 唯一标识（指定key + url + 消息头）
        String cacheRepeatKey = REPEAT_SUBMIT_KEY + url + submitKey;
        String key = redisService.getCacheObject(cacheRepeatKey);
        if (key == null) {
            redisService.setCacheObject(cacheRepeatKey, "", interval, TimeUnit.MILLISECONDS);
            KEY_CACHE.set(cacheRepeatKey);
        } else {
            String message = repeatSubmit.message();
            if (StringUtils.startsWith(message, "{") && StringUtils.endsWith(message, "}")) {
                message =
                        MessageUtils.message(
                                StringUtils.substring(message, 1, message.length() - 1));
            }
            throw new BusinessException(message);
        }
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
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile
                || o instanceof HttpServletRequest
                || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }

    /** 参数拼装 */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null) {
            for (Object o : paramsArray) {
                if (ObjectUtil.isNotNull(o) && !isFilterObject(o)) {
                    try {
                        params.append(ExJsonUtils.toJsonString(o)).append(" ");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return params.toString().trim();
    }
}

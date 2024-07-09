package cc.flyflow.web.config;

import cc.flyflow.biz.constants.SystemConstants;
import cc.flyflow.common.exception.BusinessException;
import cc.flyflow.common.exception.ResultCode;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.comparator.VersionComparator;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Huijun Zhao
 * @description
 * @date 2023-08-04 10:20
 */
@Configuration
@Slf4j
public class VersionInterceptor implements WebMvcConfigurer {

    // 创建缓存，默认10秒过期
    public static TimedCache<String, String> timedCache = CacheUtil.newTimedCache(10000);
    @Resource private RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        timedCache.schedulePrune(3000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                        new HandlerInterceptor() {
                            @Override
                            public boolean preHandle(
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    Object handler)
                                    throws Exception {
                                String flyflowVersion = request.getHeader("FlyflowVersion");
                                log.debug("前端请求版本号：{}", flyflowVersion);
                                if (StrUtil.isBlank(flyflowVersion)) {
                                    return true;
                                }
                                String version =
                                        timedCache.get(SystemConstants.VERSION_REDIS_KEY, false);
                                log.debug("本地缓存获取的版本号:{}", version);
                                if (StrUtil.isBlank(version)) {
                                    Object o =
                                            redisTemplate
                                                    .opsForValue()
                                                    .get(SystemConstants.VERSION_REDIS_KEY);
                                    log.debug("从redis中获取的版本号:{}", o);
                                    if (o == null) {
                                        return true;
                                    }
                                    version = Convert.toStr(o);

                                    timedCache.put(SystemConstants.VERSION_REDIS_KEY, version);
                                }
                                // 1
                                int compare =
                                        VersionComparator.INSTANCE.compare(flyflowVersion, version);
                                if (compare < 0) {
                                    throw new BusinessException(ResultCode.WEB_VERSION_LOW);
                                }
                                return true;
                            }
                        })
                .addPathPatterns("/process/**", "/process-instance/**", "/task/**");
    }
}
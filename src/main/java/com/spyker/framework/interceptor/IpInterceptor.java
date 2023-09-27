package com.spyker.framework.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class IpInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userIp = request.getHeader("X-Forwarded-For");

        if (userIp == null || userIp.length() == 0) {
            userIp = request.getHeader("WL-Proxy-Client-IP");
        }

        if (userIp == null || userIp.length() == 0) {
            userIp = request.getRemoteAddr();
        }

        log.info("--------------- User IP Address: " + userIp);

        return true;
    }

}
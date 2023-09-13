package com.spyker.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IpInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

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
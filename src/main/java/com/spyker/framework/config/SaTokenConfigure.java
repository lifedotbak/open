package com.spyker.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

	// Sa-Token 整合 jwt (Simple 简单模式)
	@Bean
	public StpLogic getStpLogicJwt() {
		return new StpLogicJwtForSimple();
	}

	// 注册 Sa-Token 拦截器，打开注解式鉴权功能
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册 Sa-Token 拦截器，打开注解式鉴权功能

		registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");

		// // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
		// registry.addInterceptor(new SaInterceptor(handler -> {
		//
		// // 功能说明: 使用 .html , .css 或者 .js 结尾的任意路由都将跳过, 不会进入 check 方法
		// SaRouter.match("/**").notMatch("*.html", "*.css", "*.js").check(r ->
		// StpUtil.checkLogin());
		//
		// // 角色校验 -- 拦截以 admin 开头的路由，必须具备 admin 角色或者 super-admin 角色才可以通过认证
		// SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("admin",
		// "super-admin"));
		//
		// // 权限校验 -- 不同模块校验不同权限
		// SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
		// SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
		// SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
		// SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
		// SaRouter.match("/notice/**", r -> StpUtil.checkPermission("notice"));
		// SaRouter.match("/comment/**", r -> StpUtil.checkPermission("comment"));
		//
		// })).addPathPatterns("/**");
	}
}
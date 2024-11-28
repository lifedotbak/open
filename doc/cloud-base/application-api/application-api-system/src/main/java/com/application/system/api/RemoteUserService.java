package com.application.system.api;

import com.application.common.core.constant.SecurityConstants;
import com.application.common.core.constant.ServiceNameConstants;
import com.application.common.core.domain.R;
import com.application.system.api.domain.SysUser;
import com.application.system.api.factory.RemoteUserFallbackFactory;
import com.application.system.api.model.LoginUser;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 用户服务
 *
 * @author ruoyi
 */
@Component
@FeignClient(
        contextId = "remoteUserService",
        value = ServiceNameConstants.SYSTEM_SERVICE,
        fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/user/info/{username}")
    public R<LoginUser> getUserInfo(
            @PathVariable("username") String username,
            @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/user/register")
    public R<Boolean> registerUserInfo(
            @RequestBody SysUser sysUser,
            @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 记录用户登录IP地址和登录时间
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PutMapping("/user/recordlogin")
    public R<Boolean> recordUserLogin(
            @RequestBody SysUser sysUser,
            @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
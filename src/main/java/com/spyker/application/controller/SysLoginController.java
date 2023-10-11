package com.spyker.application.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.spyker.framework.domain.AjaxResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统访问记录 前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@RestController
@Tag(name = "系统登录", description = "系统登录")
@RequestMapping("/application/login")
@Slf4j
@SaCheckLogin
public class SysLoginController {

    /**
     * 登录方法
     *
     * @return 结果
     */
    @PostMapping("/login")
    @SaIgnore
    public AjaxResult login() {
        AjaxResult ajax = AjaxResult.success();

        StpUtil.login("11111");

        return ajax;
    }

    @PostMapping("/islogin")
    public AjaxResult islogin() {
        AjaxResult ajax = AjaxResult.success();

        return ajax.put("isLogin", StpUtil.isLogin());
    }

}
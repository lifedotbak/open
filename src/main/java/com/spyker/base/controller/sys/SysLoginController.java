package com.spyker.base.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.spyker.base.service.SysUserService;
import com.spyker.framework.response.RestResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/sys/login")
@Slf4j
@SaCheckLogin
@RequiredArgsConstructor
public class SysLoginController {

    private final SysUserService sysuserService;

    /**
     * 登录方法
     *
     * @return 结果
     */
    @PostMapping("/login")
    @SaIgnore
    public RestResponse<?> login(String userName, String password) {

        if (sysuserService.login(userName, password)) {
            return RestResponse.success();
        }

        return RestResponse.error(-1, "login error");
    }

    @PostMapping("/logout")
    public RestResponse<?> logout() {

        StpUtil.logout();

        return RestResponse.success();
    }

}
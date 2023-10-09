package com.spyker.application.common.controller;

import com.spyker.application.common.service.SysLoginService;
import com.spyker.framework.constant.Constants;
import com.spyker.framework.domain.AjaxResult;
import com.spyker.framework.domain.model.LoginBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/application/common/login")
@Slf4j
public class SysLoginController {

    @Autowired
    private SysLoginService sysLoginService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        //         生成令牌
        String token = sysLoginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

}
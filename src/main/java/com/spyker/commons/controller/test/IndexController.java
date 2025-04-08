package com.spyker.commons.controller.test;

import com.spyker.commons.service.SysUserService;
import com.spyker.framework.log.annotation.ControllerLogAnnotation;
import com.spyker.framework.ratelimiter.RateLimiting;
import com.spyker.framework.web.response.RestResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试用例类", description = "测试用例类")
@RequestMapping("/test/index")
@RestController
public class IndexController {

    @Autowired SysUserService sysUserService;

    @ControllerLogAnnotation(
            title = "测试%s接口%s",
            titleParamNames = {"id", "name"})
    @GetMapping("/controllerLogAnnotation")
    public RestResponse<String> controllerLogAnnotation(String id, String name) {
        return RestResponse.success("limiting1");
    }

    @GetMapping("/deleteUserThrowException")
    public RestResponse<String> deleteUserThrowException(String id) {

        sysUserService.deleteUserThrowException(id);

        return RestResponse.success("limiting1");
    }

    /**
     * 使用限流注解的接口1
     *
     * @return RestResponse<String>
     */
    @GetMapping("/limit1")
    @RateLimiting(limitNum = 1, name = "limiting1")
    public RestResponse<String> limit1() {
        return RestResponse.success("limiting1");
    }

    /**
     * 使用限流注解的接口2
     *
     * @return RestResponse<String>
     */
    @GetMapping("/limit2")
    @RateLimiting(limitNum = 5, name = "limiting2")
    public RestResponse<String> limit2() {
        return RestResponse.success("limiting2");
    }
}

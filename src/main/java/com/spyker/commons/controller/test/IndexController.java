package com.spyker.commons.controller.test;

import com.spyker.framework.ratelimiter.Limiting;
import com.spyker.framework.response.RestResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试用例类", description = "测试用例类")
@RequestMapping("/test/index")
@RestController
public class IndexController {

    /**
     * 使用限流注解的接口1
     *
     * @return RestResponse<String>
     */
    @GetMapping("/limit1")
    @Limiting(limitNum = 1, name = "limiting1")
    public RestResponse<String> limit1() {
        return RestResponse.success("limiting1");
    }

    /**
     * 使用限流注解的接口2
     *
     * @return RestResponse<String>
     */
    @GetMapping("/limit2")
    @Limiting(limitNum = 5, name = "limiting2")
    public RestResponse<String> limit2() {
        return RestResponse.success("limiting2");
    }
}
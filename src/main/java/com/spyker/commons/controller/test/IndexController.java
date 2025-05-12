package com.spyker.commons.controller.test;

import com.spyker.commons.excel.ExcelTestData;
import com.spyker.commons.service.SysUserService;
import com.spyker.framework.log.annotation.ControllerLogAnnotation;
import com.spyker.framework.poi.ExcelUtil;
import com.spyker.framework.ratelimiter.RateLimiting;
import com.spyker.framework.web.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "测试用例类", description = "测试用例类")
@RequestMapping("/test/index")
@RestController
public class IndexController {

    @Autowired SysUserService sysUserService;

    public static void main(String[] args) {
        List<ExcelTestData> excelList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ExcelTestData data = new ExcelTestData();

            data.setName("java265.com-" + i);
            data.setAge("age" + i);
            data.setSex("男");

            excelList.add(data);
        }

        ExcelUtil<ExcelTestData> util = new ExcelUtil<>(ExcelTestData.class);

        util.exportExcel2File("D:/xxxx/", excelList, "用户数据");
    }

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

    @Operation(summary = "数据导出", description = "数据导出")
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {

        List<ExcelTestData> excelList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ExcelTestData data = new ExcelTestData();

            data.setName("java265.com-" + i);
            data.setAge("age" + i);
            data.setSex("男");

            excelList.add(data);
        }

        ExcelUtil<ExcelTestData> util = new ExcelUtil<>(ExcelTestData.class);

        util.exportExcel2HttpServletResponse(response, excelList, "用户数据");
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

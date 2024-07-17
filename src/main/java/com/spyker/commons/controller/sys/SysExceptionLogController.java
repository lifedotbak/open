package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysExceptionLog;
import com.spyker.commons.search.SysExceptionLogSearch;
import com.spyker.commons.service.SysExceptionLogService;
import com.spyker.framework.constants.BusinessTypeEnum;
import com.spyker.framework.log.annotation.ControllerLogAnnotation;
import com.spyker.framework.web.BaseController;
import com.spyker.framework.web.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志记录 前端控制器
 *
 * @author CodeGenerator
 * @since 2024-07-16
 */
@Tag(name = "操作日志记录", description = "操作日志记录")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/sys-exception-log")
@Slf4j
public class SysExceptionLogController extends BaseController {

    private final SysExceptionLogService sysExceptionLogService;

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "操作日志记录--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<SysExceptionLog>> listPage(
            @ModelAttribute SysExceptionLogSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysExceptionLog> page = new Page<>(current, size);

        page = sysExceptionLogService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "操作日志记录--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<SysExceptionLog> detail(@PathVariable("id") String id) {

        SysExceptionLog result = sysExceptionLogService.get(id);

        return RestResponse.success(result);
    }
}
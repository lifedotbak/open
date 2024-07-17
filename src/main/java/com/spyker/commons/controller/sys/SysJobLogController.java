package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysJobLog;
import com.spyker.commons.search.SysJobLogSearch;
import com.spyker.commons.service.SysJobLogService;
import com.spyker.framework.web.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务调度日志表 前端控制器
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "定时任务调度日志表", description = "定时任务调度日志表")
@RequestMapping("/sys/sys-job-log")
@Slf4j
@SaCheckLogin
public class SysJobLogController {

    private final SysJobLogService sysJobLogService;

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysJobLog>> listPage(SysJobLogSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysJobLog> page = new Page<>(current, size);

        page = sysJobLogService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysJobLog> detail(@RequestParam String id) {
        SysJobLog result = sysJobLogService.get(id);

        return RestResponse.success(result);
    }
}
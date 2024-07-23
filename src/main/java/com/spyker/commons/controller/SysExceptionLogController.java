package com.spyker.commons.controller;

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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 异常日志表 前端控制器
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Tag(name = "异常日志表", description = "异常日志表")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/sys-exception-log")
@Slf4j
public class SysExceptionLogController extends BaseController {

    private final SysExceptionLogService sysExceptionLogService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "异常日志表--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<SysExceptionLog>> list(SysExceptionLogSearch search) {

        List<SysExceptionLog> result = sysExceptionLogService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "异常日志表--列表（分页）", businessType = BusinessTypeEnum.QUERY)
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
    @ControllerLogAnnotation(title = "异常日志表--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<SysExceptionLog> detail(@PathVariable("id") String id) {

        SysExceptionLog result = sysExceptionLogService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "异常日志表--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<SysExceptionLog> add(@RequestBody SysExceptionLog add) {

        sysExceptionLogService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "异常日志表--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<SysExceptionLog> update(
            @PathVariable("id") String id, @RequestBody SysExceptionLog update) {

        update.setId(id);

        sysExceptionLogService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "异常日志表--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<SysExceptionLog> delete(@PathVariable("id") String id) {

        sysExceptionLogService.delete(id);

        return RestResponse.success();
    }
}
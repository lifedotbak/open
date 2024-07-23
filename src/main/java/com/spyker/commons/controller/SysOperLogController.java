package com.spyker.commons.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysOperLog;
import com.spyker.commons.search.SysOperLogSearch;
import com.spyker.commons.service.SysOperLogService;
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
 * 操作日志记录 前端控制器
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Tag(name = "操作日志记录", description = "操作日志记录")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/sys-oper-log")
@Slf4j
public class SysOperLogController extends BaseController {

    private final SysOperLogService sysOperLogService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "操作日志记录--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<SysOperLog>> list(SysOperLogSearch search) {

        List<SysOperLog> result = sysOperLogService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "操作日志记录--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<SysOperLog>> listPage(@ModelAttribute SysOperLogSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysOperLog> page = new Page<>(current, size);

        page = sysOperLogService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "操作日志记录--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<SysOperLog> detail(@PathVariable("id") String id) {

        SysOperLog result = sysOperLogService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "操作日志记录--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<SysOperLog> add(@RequestBody SysOperLog add) {

        sysOperLogService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "操作日志记录--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<SysOperLog> update(
            @PathVariable("id") String id, @RequestBody SysOperLog update) {

        update.setId(id);

        sysOperLogService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "操作日志记录--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<SysOperLog> delete(@PathVariable("id") String id) {

        sysOperLogService.delete(id);

        return RestResponse.success();
    }
}
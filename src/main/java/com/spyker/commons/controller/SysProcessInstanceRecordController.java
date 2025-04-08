package com.spyker.commons.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysProcessInstanceRecord;
import com.spyker.commons.search.SysProcessInstanceRecordSearch;
import com.spyker.commons.service.SysProcessInstanceRecordService;
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

/** 流程记录 前端控制器 */
@Tag(name = "流程记录", description = "流程记录")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/sys-process-instance-record")
@Slf4j
public class SysProcessInstanceRecordController extends BaseController {

    private final SysProcessInstanceRecordService sysProcessInstanceRecordService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "流程记录--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<SysProcessInstanceRecord> add(@RequestBody SysProcessInstanceRecord add) {

        sysProcessInstanceRecordService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "流程记录--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<SysProcessInstanceRecord> delete(@PathVariable("id") String id) {

        sysProcessInstanceRecordService.delete(id);

        return RestResponse.success();
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "流程记录--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<SysProcessInstanceRecord> detail(@PathVariable("id") String id) {

        SysProcessInstanceRecord result = sysProcessInstanceRecordService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "流程记录--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<SysProcessInstanceRecord>> list(
            SysProcessInstanceRecordSearch search) {

        List<SysProcessInstanceRecord> result = sysProcessInstanceRecordService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "流程记录--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<SysProcessInstanceRecord>> listPage(
            @ModelAttribute SysProcessInstanceRecordSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysProcessInstanceRecord> page = new Page<>(current, size);

        page = sysProcessInstanceRecordService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "流程记录--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<SysProcessInstanceRecord> update(
            @PathVariable("id") String id, @RequestBody SysProcessInstanceRecord update) {

        update.setId(id);

        sysProcessInstanceRecordService.update(update);

        return RestResponse.success();
    }
}

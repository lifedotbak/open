package com.spyker.commons.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysProcessMain;
import com.spyker.commons.search.SysProcessMainSearch;
import com.spyker.commons.service.SysProcessMainService;
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

/** 流程主表 前端控制器 */
@Tag(name = "流程主表", description = "流程主表")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/sys-process-main")
@Slf4j
public class SysProcessMainController extends BaseController {

    private final SysProcessMainService sysProcessMainService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "流程主表--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<SysProcessMain> add(@RequestBody SysProcessMain add) {

        sysProcessMainService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "流程主表--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<SysProcessMain> delete(@PathVariable("id") String id) {

        sysProcessMainService.delete(id);

        return RestResponse.success();
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "流程主表--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<SysProcessMain> detail(@PathVariable("id") String id) {

        SysProcessMain result = sysProcessMainService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "流程主表--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<SysProcessMain>> list(SysProcessMainSearch search) {

        List<SysProcessMain> result = sysProcessMainService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "流程主表--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<SysProcessMain>> listPage(
            @ModelAttribute SysProcessMainSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysProcessMain> page = new Page<>(current, size);

        page = sysProcessMainService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "流程主表--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<SysProcessMain> update(
            @PathVariable("id") String id, @RequestBody SysProcessMain update) {

        update.setId(id);

        sysProcessMainService.update(update);

        return RestResponse.success();
    }
}

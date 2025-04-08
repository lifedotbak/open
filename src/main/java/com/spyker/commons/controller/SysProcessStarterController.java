package com.spyker.commons.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysProcessStarter;
import com.spyker.commons.search.SysProcessStarterSearch;
import com.spyker.commons.service.SysProcessStarterService;
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

/** 流程发起人范围 前端控制器 */
@Tag(name = "流程发起人范围", description = "流程发起人范围")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/sys-process-starter")
@Slf4j
public class SysProcessStarterController extends BaseController {

    private final SysProcessStarterService sysProcessStarterService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "流程发起人范围--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<SysProcessStarter> add(@RequestBody SysProcessStarter add) {

        sysProcessStarterService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "流程发起人范围--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<SysProcessStarter> delete(@PathVariable("id") String id) {

        sysProcessStarterService.delete(id);

        return RestResponse.success();
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "流程发起人范围--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<SysProcessStarter> detail(@PathVariable("id") String id) {

        SysProcessStarter result = sysProcessStarterService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "流程发起人范围--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<SysProcessStarter>> list(SysProcessStarterSearch search) {

        List<SysProcessStarter> result = sysProcessStarterService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "流程发起人范围--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<SysProcessStarter>> listPage(
            @ModelAttribute SysProcessStarterSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysProcessStarter> page = new Page<>(current, size);

        page = sysProcessStarterService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "流程发起人范围--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<SysProcessStarter> update(
            @PathVariable("id") String id, @RequestBody SysProcessStarter update) {

        update.setId(id);

        sysProcessStarterService.update(update);

        return RestResponse.success();
    }
}

package com.spyker.commons.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysProcessGroup;
import com.spyker.commons.search.SysProcessGroupSearch;
import com.spyker.commons.service.SysProcessGroupService;
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

/** 前端控制器 */
@Tag(name = "", description = "")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/sys-process-group")
@Slf4j
public class SysProcessGroupController extends BaseController {

    private final SysProcessGroupService sysProcessGroupService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<SysProcessGroup> add(@RequestBody SysProcessGroup add) {

        sysProcessGroupService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<SysProcessGroup> delete(@PathVariable("id") String id) {

        sysProcessGroupService.delete(id);

        return RestResponse.success();
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<SysProcessGroup> detail(@PathVariable("id") String id) {

        SysProcessGroup result = sysProcessGroupService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<SysProcessGroup>> list(SysProcessGroupSearch search) {

        List<SysProcessGroup> result = sysProcessGroupService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<SysProcessGroup>> listPage(
            @ModelAttribute SysProcessGroupSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysProcessGroup> page = new Page<>(current, size);

        page = sysProcessGroupService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<SysProcessGroup> update(
            @PathVariable("id") String id, @RequestBody SysProcessGroup update) {

        update.setId(id);

        sysProcessGroupService.update(update);

        return RestResponse.success();
    }
}

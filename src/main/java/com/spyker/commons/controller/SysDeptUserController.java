package com.spyker.commons.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysDeptUser;
import com.spyker.commons.search.SysDeptUserSearch;
import com.spyker.commons.service.SysDeptUserService;
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

/** 部门-主管表 前端控制器 */
@Tag(name = "部门-主管表", description = "部门-主管表")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/sys-dept-user")
@Slf4j
public class SysDeptUserController extends BaseController {

    private final SysDeptUserService sysDeptUserService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "部门-主管表--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<SysDeptUser> add(@RequestBody SysDeptUser add) {

        sysDeptUserService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "部门-主管表--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<SysDeptUser> delete(@PathVariable("id") String id) {

        sysDeptUserService.delete(id);

        return RestResponse.success();
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "部门-主管表--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<SysDeptUser> detail(@PathVariable("id") String id) {

        SysDeptUser result = sysDeptUserService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "部门-主管表--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<SysDeptUser>> list(SysDeptUserSearch search) {

        List<SysDeptUser> result = sysDeptUserService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "部门-主管表--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<SysDeptUser>> listPage(@ModelAttribute SysDeptUserSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysDeptUser> page = new Page<>(current, size);

        page = sysDeptUserService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "部门-主管表--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<SysDeptUser> update(
            @PathVariable("id") String id, @RequestBody SysDeptUser update) {

        update.setId(id);

        sysDeptUserService.update(update);

        return RestResponse.success();
    }
}

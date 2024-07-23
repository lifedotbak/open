package com.spyker.commons.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysUser;
import com.spyker.commons.search.SysUserSearch;
import com.spyker.commons.service.SysUserService;
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
 * 用户信息表 前端控制器
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Tag(name = "用户信息表", description = "用户信息表")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/sys-user")
@Slf4j
public class SysUserController extends BaseController {

    private final SysUserService sysUserService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "用户信息表--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<SysUser>> list(SysUserSearch search) {

        List<SysUser> result = sysUserService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "用户信息表--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<SysUser>> listPage(@ModelAttribute SysUserSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysUser> page = new Page<>(current, size);

        page = sysUserService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "用户信息表--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<SysUser> detail(@PathVariable("id") String id) {

        SysUser result = sysUserService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "用户信息表--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<SysUser> add(@RequestBody SysUser add) {

        sysUserService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "用户信息表--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<SysUser> update(
            @PathVariable("id") String id, @RequestBody SysUser update) {

        update.setId(id);

        sysUserService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "用户信息表--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<SysUser> delete(@PathVariable("id") String id) {

        sysUserService.delete(id);

        return RestResponse.success();
    }
}
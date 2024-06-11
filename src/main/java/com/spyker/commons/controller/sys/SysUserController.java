package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysUser;
import com.spyker.commons.search.SysUserSearch;
import com.spyker.commons.service.SysUserService;
import com.spyker.framework.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息表 前端控制器
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "用户信息表", description = "用户信息表")
@RequestMapping("/sys/sys-user")
@Slf4j
@SaCheckLogin
public class SysUserController {

    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    private final SysUserService sysUserService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("list")
    public RestResponse<List<SysUser>> list(SysUserSearch search) {

        List<SysUser> result = sysUserService.query(search);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysUser>> list_page(SysUserSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysUser> page = new Page<>(current, size);

        page = sysUserService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysUser> detail(@RequestParam String id) {
        SysUser result = sysUserService.get(id);

        return RestResponse.success(result);
    }

    @SaCheckRole("admin")
    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody SysUser add) {

        return sysUserService.insert(add);
    }

    @SaCheckRole("admin")
    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody SysUser update) {

        return sysUserService.update(update);
    }

    @SaCheckRole("admin")
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

        return sysUserService.delete(id);
    }
}
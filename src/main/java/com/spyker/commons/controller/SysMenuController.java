package com.spyker.commons.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysMenu;
import com.spyker.commons.search.SysMenuSearch;
import com.spyker.commons.service.SysMenuService;
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

/** 菜单管理 前端控制器 */
@Tag(name = "菜单管理", description = "菜单管理")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/sys-menu")
@Slf4j
public class SysMenuController extends BaseController {

    private final SysMenuService sysMenuService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "菜单管理--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<SysMenu> add(@RequestBody SysMenu add) {

        sysMenuService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "菜单管理--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<SysMenu> delete(@PathVariable("id") String id) {

        sysMenuService.delete(id);

        return RestResponse.success();
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "菜单管理--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<SysMenu> detail(@PathVariable("id") String id) {

        SysMenu result = sysMenuService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "菜单管理--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<SysMenu>> list(SysMenuSearch search) {

        List<SysMenu> result = sysMenuService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "菜单管理--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<SysMenu>> listPage(@ModelAttribute SysMenuSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysMenu> page = new Page<>(current, size);

        page = sysMenuService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "菜单管理--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<SysMenu> update(
            @PathVariable("id") String id, @RequestBody SysMenu update) {

        update.setId(id);

        sysMenuService.update(update);

        return RestResponse.success();
    }
}

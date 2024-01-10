package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysConfig;
import com.spyker.commons.search.SysConfigSearch;
import com.spyker.commons.service.SysConfigService;
import com.spyker.framework.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

/**
 * 参数配置表 前端控制器
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "参数配置表", description = "参数配置表")
@RequestMapping("/sys/sys-config")
@Slf4j
@SaCheckLogin
public class SysConfigController {

    private final SysConfigService sysConfigService;

    //    @SaCheckPermission(value = "user:get",orRole = "admin")
    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysConfig>> list_page(SysConfigSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysConfig> page = new Page<>(current, size);

        page = sysConfigService.queryPage(page, search);

        return RestResponse.success(page);
    }

    //    @SaCheckPermission(value = "user:get",orRole = "admin")
    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysConfig> detail(@RequestParam String id) {
        SysConfig result = sysConfigService.get(id);

        return RestResponse.success(result);
    }

    @SaCheckRole("admin")
    @SaCheckPermission("admin:add")
    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody SysConfig add) {

        sysConfigService.insert(add);

        return RestResponse.success();
    }

    @SaCheckRole("admin")
    @SaCheckPermission("admin:update")
    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody SysConfig update) {

        sysConfigService.update(update);

        return RestResponse.success();
    }

    @SaCheckRole("admin")
    @SaCheckPermission("admin:delete")
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

        sysConfigService.delete(id);

        return RestResponse.success();
    }
}
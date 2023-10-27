package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysMenu;
import com.spyker.commons.search.SysMenuSearch;
import com.spyker.commons.service.SysMenuService;
import com.spyker.framework.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "菜单权限表", description = "菜单权限表")
@RequestMapping("/sys/sys-menu")
@Slf4j
@SaCheckLogin
@SaCheckRole("admin")
public class SysMenuController {

    private final SysMenuService sysMenuService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("list")
    public RestResponse<List<SysMenu>> list(SysMenuSearch search) {

        List<SysMenu> result = sysMenuService.query(search);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysMenu>> list_page(SysMenuSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysMenu> page = new Page<>(current, size);

        page = sysMenuService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysMenu> detail(@RequestParam String id) {
        SysMenu result = sysMenuService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody SysMenu add) {

        return sysMenuService.insert(add);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody SysMenu update) {

        return sysMenuService.update(update);
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

        return sysMenuService.delete(id);
    }

}
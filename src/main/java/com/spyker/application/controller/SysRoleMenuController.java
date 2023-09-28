package com.spyker.application.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.application.entity.SysRoleMenu;
import com.spyker.application.search.SysRoleMenuSearch;
import com.spyker.application.service.SysRoleMenuService;
import com.spyker.framework.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@RequiredArgsConstructor
@RestController
@Tag(name = "角色和菜单关联表", description = "角色和菜单关联表")
@RequestMapping("/application/sys-role-menu")
@Slf4j
public class SysRoleMenuController {

    private final SysRoleMenuService sysRoleMenuService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("list")
    public RestResponse<List<SysRoleMenu>> list(SysRoleMenuSearch search) {

        List<SysRoleMenu> result = sysRoleMenuService.query(search);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysRoleMenu>> list_page(SysRoleMenuSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysRoleMenu> page = new Page<>(current, size);

        page = sysRoleMenuService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysRoleMenu> detail(@RequestParam String id) {
        SysRoleMenu result = sysRoleMenuService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody SysRoleMenu add) {

        return sysRoleMenuService.insert(add);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody SysRoleMenu update) {

        return sysRoleMenuService.update(update);
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

        return sysRoleMenuService.delete(id);
    }

}
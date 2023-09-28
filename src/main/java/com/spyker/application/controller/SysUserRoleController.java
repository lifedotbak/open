package com.spyker.application.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.application.entity.SysUserRole;
import com.spyker.application.search.SysUserRoleSearch;
import com.spyker.application.service.SysUserRoleService;
import com.spyker.framework.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户和角色关联表 前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@RequiredArgsConstructor
@RestController
@Tag(name = "用户和角色关联表", description = "用户和角色关联表")
@RequestMapping("/application/sys-user-role")
@Slf4j
public class SysUserRoleController {

    private final SysUserRoleService sysUserRoleService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("list")
    public RestResponse<List<SysUserRole>> list(SysUserRoleSearch search) {

        List<SysUserRole> result = sysUserRoleService.query(search);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysUserRole>> list_page(SysUserRoleSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysUserRole> page = new Page<>(current, size);

        page = sysUserRoleService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysUserRole> detail(@RequestParam String id) {
        SysUserRole result = sysUserRoleService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody SysUserRole add) {

        return sysUserRoleService.insert(add);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody SysUserRole update) {

        return sysUserRoleService.update(update);
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

        return sysUserRoleService.delete(id);
    }

}
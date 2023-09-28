package com.spyker.application.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.application.entity.SysRoleDept;
import com.spyker.application.search.SysRoleDeptSearch;
import com.spyker.application.service.SysRoleDeptService;
import com.spyker.framework.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色和部门关联表 前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@RequiredArgsConstructor
@RestController
@Tag(name = "角色和部门关联表", description = "角色和部门关联表")
@RequestMapping("/application/sys-role-dept")
@Slf4j
public class SysRoleDeptController {

    private final SysRoleDeptService sysRoleDeptService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("list")
    public RestResponse<List<SysRoleDept>> list(SysRoleDeptSearch search) {

        List<SysRoleDept> result = sysRoleDeptService.query(search);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysRoleDept>> list_page(SysRoleDeptSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysRoleDept> page = new Page<>(current, size);

        page = sysRoleDeptService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysRoleDept> detail(@RequestParam String id) {
        SysRoleDept result = sysRoleDeptService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody SysRoleDept add) {

        return sysRoleDeptService.insert(add);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody SysRoleDept update) {

        return sysRoleDeptService.update(update);
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

        return sysRoleDeptService.delete(id);
    }

}
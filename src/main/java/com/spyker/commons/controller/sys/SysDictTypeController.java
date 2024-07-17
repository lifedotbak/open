package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysDictType;
import com.spyker.commons.search.SysDictTypeSearch;
import com.spyker.commons.service.SysDictTypeService;
import com.spyker.framework.web.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典类型表 前端控制器
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "字典类型表", description = "字典类型表")
@RequestMapping("/sys/sys-dict-type")
@Slf4j
@SaCheckLogin
public class SysDictTypeController {

    private final SysDictTypeService sysDictTypeService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("list")
    public RestResponse<List<SysDictType>> list(SysDictTypeSearch search) {

        List<SysDictType> result = sysDictTypeService.query(search);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysDictType>> listPage(SysDictTypeSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysDictType> page = new Page<>(current, size);

        page = sysDictTypeService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysDictType> detail(@RequestParam String id) {
        SysDictType result = sysDictTypeService.get(id);

        return RestResponse.success(result);
    }

    @SaCheckRole("admin")
    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<SysDictType> add(@RequestBody SysDictType add) {

        sysDictTypeService.insert(add);

        return RestResponse.success();
    }

    @SaCheckRole("admin")
    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<SysDictType> update(@RequestBody SysDictType update) {

        sysDictTypeService.update(update);

        return RestResponse.success();
    }

    @SaCheckRole("admin")
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<SysDictType> delete(@RequestParam String id) {

        sysDictTypeService.delete(id);

        return RestResponse.success();
    }
}
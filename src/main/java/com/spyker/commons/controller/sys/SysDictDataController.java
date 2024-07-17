package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysDictData;
import com.spyker.commons.search.SysDictDataSearch;
import com.spyker.commons.service.SysDictDataService;
import com.spyker.framework.web.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

/**
 * 字典数据表 前端控制器
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "字典数据表", description = "字典数据表")
@RequestMapping("/sys/sys-dict-data")
@Slf4j
@SaCheckLogin
public class SysDictDataController {

    private final SysDictDataService sysDictDataService;

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysDictData>> listPage(SysDictDataSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysDictData> page = new Page<>(current, size);

        page = sysDictDataService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysDictData> detail(@RequestParam String id) {
        SysDictData result = sysDictDataService.get(id);

        return RestResponse.success(result);
    }

    @SaCheckRole("admin")
    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<SysDictData> add(@RequestBody SysDictData add) {

        sysDictDataService.insert(add);

        return RestResponse.success();
    }

    @SaCheckRole("admin")
    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<SysDictData> update(@RequestBody SysDictData update) {

        sysDictDataService.update(update);

        return RestResponse.success();
    }

    @SaCheckRole("admin")
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<SysDictData> delete(@RequestParam String id) {

        sysDictDataService.delete(id);

        return RestResponse.success();
    }
}
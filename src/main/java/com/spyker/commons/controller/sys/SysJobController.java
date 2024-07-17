package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysJob;
import com.spyker.commons.search.SysJobSearch;
import com.spyker.commons.service.SysJobService;
import com.spyker.framework.web.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 定时任务调度表 前端控制器
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "定时任务调度表", description = "定时任务调度表")
@RequestMapping("/sys/sys-job")
@Slf4j
@SaCheckLogin
public class SysJobController {

    private final SysJobService sysJobService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("list")
    public RestResponse<List<SysJob>> list(SysJobSearch search) {

        List<SysJob> result = sysJobService.query(search);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysJob>> listPage(SysJobSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysJob> page = new Page<>(current, size);

        page = sysJobService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysJob> detail(@RequestParam String id) {
        SysJob result = sysJobService.get(id);

        return RestResponse.success(result);
    }

    @SaCheckRole("admin")
    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody SysJob add) {

        return sysJobService.insert(add);
    }

    @SaCheckRole("admin")
    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody SysJob update) {

        return sysJobService.update(update);
    }

    @SaCheckRole("admin")
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

        return sysJobService.delete(id);
    }
}
package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysNotice;
import com.spyker.commons.search.SysNoticeSearch;
import com.spyker.commons.service.SysNoticeService;
import com.spyker.framework.web.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

/**
 * 通知公告表 前端控制器
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "通知公告表", description = "通知公告表")
@RequestMapping("/sys/sys-notice")
@Slf4j
@SaCheckLogin
public class SysNoticeController {

    private final SysNoticeService sysNoticeService;

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysNotice>> list_page(SysNoticeSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysNotice> page = new Page<>(current, size);

        page = sysNoticeService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysNotice> detail(@RequestParam String id) {
        SysNotice result = sysNoticeService.get(id);

        return RestResponse.success(result);
    }

    @SaCheckRole("admin")
    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody SysNotice add) {

        return sysNoticeService.insert(add);
    }

    @SaCheckRole("admin")
    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody SysNotice update) {

        return sysNoticeService.update(update);
    }

    @SaCheckRole("admin")
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

        return sysNoticeService.delete(id);
    }
}
package com.spyker.application.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.application.entity.SysConfig;
import com.spyker.application.search.SysConfigSearch;
import com.spyker.application.service.SysConfigService;
import com.spyker.framework.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 参数配置表 前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@RequiredArgsConstructor
@RestController
@Tag(name = "参数配置表", description = "参数配置表")
@RequestMapping("/application/sys-config")
@Slf4j
public class SysConfigController {

    private final SysConfigService sysConfigService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("list")
    public RestResponse<List<SysConfig>> list(SysConfigSearch search) {

        List<SysConfig> result = sysConfigService.query(search);

        return RestResponse.success(result);
    }

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

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysConfig> detail(@RequestParam String id) {
        SysConfig result = sysConfigService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody SysConfig add) {

        return sysConfigService.insert(add);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody SysConfig update) {

        return sysConfigService.update(update);
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

        return sysConfigService.delete(id);
    }

}
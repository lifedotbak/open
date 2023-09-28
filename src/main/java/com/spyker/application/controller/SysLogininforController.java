package com.spyker.application.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.application.entity.SysLogininfor;
import com.spyker.application.search.SysLogininforSearch;
import com.spyker.application.service.SysLogininforService;
import com.spyker.framework.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统访问记录 前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@RequiredArgsConstructor
@RestController
@Tag(name = "系统访问记录", description = "系统访问记录")
@RequestMapping("/application/sys-logininfor")
@Slf4j
public class SysLogininforController {

    private final SysLogininforService sysLogininforService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("list")
    public RestResponse<List<SysLogininfor>> list(SysLogininforSearch search) {

        List<SysLogininfor> result = sysLogininforService.query(search);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysLogininfor>> list_page(SysLogininforSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysLogininfor> page = new Page<>(current, size);

        page = sysLogininforService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysLogininfor> detail(@RequestParam String id) {
        SysLogininfor result = sysLogininforService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody SysLogininfor add) {

        return sysLogininforService.insert(add);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody SysLogininfor update) {

        return sysLogininforService.update(update);
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

        return sysLogininforService.delete(id);
    }

}
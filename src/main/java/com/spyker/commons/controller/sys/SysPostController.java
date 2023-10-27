package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysPost;
import com.spyker.commons.search.SysPostSearch;
import com.spyker.commons.service.SysPostService;
import com.spyker.framework.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 岗位信息表 前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "岗位信息表", description = "岗位信息表")
@RequestMapping("/sys/sys-post")
@Slf4j
@SaCheckLogin
public class SysPostController {

    private final SysPostService sysPostService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("list")
    public RestResponse<List<SysPost>> list(SysPostSearch search) {

        List<SysPost> result = sysPostService.query(search);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysPost>> list_page(SysPostSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysPost> page = new Page<>(current, size);

        page = sysPostService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysPost> detail(@RequestParam String id) {
        SysPost result = sysPostService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody SysPost add) {

        return sysPostService.insert(add);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody SysPost update) {

        return sysPostService.update(update);
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

        return sysPostService.delete(id);
    }

}
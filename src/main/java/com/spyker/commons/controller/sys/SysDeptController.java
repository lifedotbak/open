package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysDept;
import com.spyker.commons.search.SysDeptSearch;
import com.spyker.commons.service.SysDeptService;
import com.spyker.framework.enums.BusinessType;
import com.spyker.framework.log.Log;
import com.spyker.framework.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门表 前端控制器
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Tag(name = "部门表", description = "部门表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/sys-dept")
@Slf4j
@SaCheckLogin
public class SysDeptController {

    private final SysDeptService sysDeptService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("list")
    public RestResponse<List<SysDept>> list(SysDeptSearch search) {

        List<SysDept> result = sysDeptService.query(search);

        log.info("--->{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    @Log(title = "部门表--列表（分页）", businessType = BusinessType.QUERY)
    public RestResponse<IPage<SysDept>> list_page(SysDeptSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysDept> page = new Page<>(current, size);

        page = sysDeptService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysDept> detail(@RequestParam String id) {
        SysDept result = sysDeptService.get(id);

        return RestResponse.success(result);
    }

    @SaCheckRole("admin")
    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody SysDept add) {

        sysDeptService.insert(add);

        return RestResponse.success();
    }

    @SaCheckRole("admin")
    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody SysDept update) {

        sysDeptService.update(update);

        return RestResponse.success();
    }

    @SaCheckRole("admin")
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

        return sysDeptService.delete(id);
    }
}
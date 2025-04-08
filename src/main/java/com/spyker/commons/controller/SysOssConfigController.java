package com.spyker.commons.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysOssConfig;
import com.spyker.commons.search.SysOssConfigSearch;
import com.spyker.commons.service.SysOssConfigService;
import com.spyker.framework.constants.BusinessTypeEnum;
import com.spyker.framework.log.annotation.ControllerLogAnnotation;
import com.spyker.framework.web.BaseController;
import com.spyker.framework.web.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** 对象存储配置表 前端控制器 */
@Tag(name = "对象存储配置表", description = "对象存储配置表")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/sys-oss-config")
@Slf4j
public class SysOssConfigController extends BaseController {

    private final SysOssConfigService sysOssConfigService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "对象存储配置表--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<SysOssConfig> add(@RequestBody SysOssConfig add) {

        sysOssConfigService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "对象存储配置表--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<SysOssConfig> delete(@PathVariable("id") String id) {

        sysOssConfigService.delete(id);

        return RestResponse.success();
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "对象存储配置表--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<SysOssConfig> detail(@PathVariable("id") String id) {

        SysOssConfig result = sysOssConfigService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "对象存储配置表--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<SysOssConfig>> list(SysOssConfigSearch search) {

        List<SysOssConfig> result = sysOssConfigService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "对象存储配置表--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<SysOssConfig>> listPage(@ModelAttribute SysOssConfigSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysOssConfig> page = new Page<>(current, size);

        page = sysOssConfigService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "对象存储配置表--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<SysOssConfig> update(
            @PathVariable("id") String id, @RequestBody SysOssConfig update) {

        update.setId(id);

        sysOssConfigService.update(update);

        return RestResponse.success();
    }
}

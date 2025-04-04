package com.spyker.commons.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysConfig;
import com.spyker.commons.search.SysConfigSearch;
import com.spyker.commons.service.SysConfigService;
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

/**
 * 参数配置表 前端控制器
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Tag(name = "参数配置表", description = "参数配置表")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/sys-config")
@Slf4j
public class SysConfigController extends BaseController {

    private final SysConfigService sysConfigService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "参数配置表--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<SysConfig>> list(SysConfigSearch search) {

        List<SysConfig> result = sysConfigService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "参数配置表--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<SysConfig>> listPage(@ModelAttribute SysConfigSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysConfig> page = new Page<>(current, size);

        page = sysConfigService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "参数配置表--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<SysConfig> detail(@PathVariable("id") String id) {

        SysConfig result = sysConfigService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "参数配置表--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<SysConfig> add(@RequestBody SysConfig add) {

        sysConfigService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "参数配置表--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<SysConfig> update(
            @PathVariable("id") String id, @RequestBody SysConfig update) {

        update.setId(id);

        sysConfigService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "参数配置表--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<SysConfig> delete(@PathVariable("id") String id) {

        sysConfigService.delete(id);

        return RestResponse.success();
    }
}

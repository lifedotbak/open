package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysOssConfig;
import com.spyker.commons.search.SysOssConfigSearch;
import com.spyker.commons.service.SysOssConfigService;
import com.spyker.framework.controller.BaseController;
import com.spyker.framework.enums.BusinessType;
import com.spyker.framework.log.Log;
import com.spyker.framework.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 对象存储配置表 前端控制器
 *
 * @author CodeGenerator
 * @since 2023-12-21
 */
@Tag(name = "对象存储配置表", description = "对象存储配置表")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/sys-oss-config")
@Slf4j
public class SysOssConfigController extends BaseController {

    private final SysOssConfigService sysOssConfigService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @Log(title = "对象存储配置表--列表", businessType = BusinessType.QUERY)
    public RestResponse<List<SysOssConfig>> list(SysOssConfigSearch search) {

        List<SysOssConfig> result = sysOssConfigService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @Log(title = "对象存储配置表--列表（分页）", businessType = BusinessType.QUERY)
    //    public RestResponse<IPage<SysOssConfig>> list_page(
    //            @ModelAttribute SysOssConfigSearch search,
    //            @ModelAttribute PageParamRequest pageParamRequest) {
    public RestResponse<IPage<SysOssConfig>> list_page(SysOssConfigSearch search) {

        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysOssConfig> page = new Page<>(current, size);

        page = sysOssConfigService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @Log(title = "对象存储配置表--详情", businessType = BusinessType.QUERY)
    public RestResponse<SysOssConfig> detail(@PathVariable("id") String id) {
        SysOssConfig result = sysOssConfigService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @Log(title = "对象存储配置表--新增", businessType = BusinessType.INSERT)
    public RestResponse<?> add(@RequestBody SysOssConfig add) {

        sysOssConfigService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{ossConfigId}")
    @Log(title = "对象存储配置表--修改", businessType = BusinessType.UPDATE)
    public RestResponse<?> update(
            @PathVariable("ossConfigId") String ossConfigId, @RequestBody SysOssConfig update) {

        update.setOssConfigId(ossConfigId);

        sysOssConfigService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{ossConfigId}")
    @Log(title = "对象存储配置表--删除", businessType = BusinessType.DELETE)
    public RestResponse<?> delete(@PathVariable("ossConfigId") String ossConfigId) {

        sysOssConfigService.delete(ossConfigId);

        return RestResponse.success();
    }
}
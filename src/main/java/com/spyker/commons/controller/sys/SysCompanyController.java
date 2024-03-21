package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysCompany;
import com.spyker.commons.search.SysCompanySearch;
import com.spyker.commons.service.SysCompanyService;
import com.spyker.framework.core.BaseController;
import com.spyker.framework.enums.BusinessType;
import com.spyker.framework.log.Log;
import com.spyker.framework.request.PageParamRequest;
import com.spyker.framework.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门表 前端控制器
 *
 * @author CodeGenerator
 * @since 2023-12-25
 */
@Tag(name = "公司表", description = "公司表")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/sys-company")
@Slf4j
public class SysCompanyController extends BaseController {

    private final SysCompanyService sysCompanyService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @Log(title = "部门表--列表", businessType = BusinessType.QUERY)
    public RestResponse<List<SysCompany>> list(SysCompanySearch search) {

        List<SysCompany> result = sysCompanyService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @Log(title = "部门表--列表（分页）", businessType = BusinessType.QUERY)
    public RestResponse<IPage<SysCompany>> listPage(
            @ModelAttribute SysCompanySearch search,
            @ModelAttribute PageParamRequest pageParamRequest) {
        int current = 1;
        int size = 10;

        if (null != pageParamRequest) {
            current = pageParamRequest.getPage();
            size = pageParamRequest.getSize();
        }

        IPage<SysCompany> page = new Page<>(current, size);

        page = sysCompanyService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @Log(title = "部门表--详情", businessType = BusinessType.QUERY)
    public RestResponse<SysCompany> detail(@PathVariable("id") String id) {

        SysCompany result = sysCompanyService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @Log(title = "部门表--新增", businessType = BusinessType.INSERT)
    public RestResponse<SysCompany> add(@RequestBody SysCompany add) {

        sysCompanyService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @Log(title = "部门表--修改", businessType = BusinessType.UPDATE)
    public RestResponse<SysCompany> update(
            @PathVariable("id") String id, @RequestBody SysCompany update) {

        update.setId(id);

        sysCompanyService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @Log(title = "部门表--删除", businessType = BusinessType.DELETE)
    public RestResponse<SysCompany> delete(@PathVariable("id") String id) {

        sysCompanyService.delete(id);

        return RestResponse.success();
    }
}
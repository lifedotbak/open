package com.spyker.commons.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysProcessInstanceCopy;
import com.spyker.commons.search.SysProcessInstanceCopySearch;
import com.spyker.commons.service.SysProcessInstanceCopyService;
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
 * 流程抄送数据 前端控制器
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Tag(name = "流程抄送数据", description = "流程抄送数据")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/sys-process-instance-copy")
@Slf4j
public class SysProcessInstanceCopyController extends BaseController {

    private final SysProcessInstanceCopyService sysProcessInstanceCopyService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "流程抄送数据--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<SysProcessInstanceCopy>> list(SysProcessInstanceCopySearch search) {

        List<SysProcessInstanceCopy> result = sysProcessInstanceCopyService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "流程抄送数据--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<SysProcessInstanceCopy>> listPage(
            @ModelAttribute SysProcessInstanceCopySearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysProcessInstanceCopy> page = new Page<>(current, size);

        page = sysProcessInstanceCopyService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "流程抄送数据--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<SysProcessInstanceCopy> detail(@PathVariable("id") String id) {

        SysProcessInstanceCopy result = sysProcessInstanceCopyService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "流程抄送数据--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<SysProcessInstanceCopy> add(@RequestBody SysProcessInstanceCopy add) {

        sysProcessInstanceCopyService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "流程抄送数据--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<SysProcessInstanceCopy> update(
            @PathVariable("id") String id, @RequestBody SysProcessInstanceCopy update) {

        update.setId(id);

        sysProcessInstanceCopyService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "流程抄送数据--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<SysProcessInstanceCopy> delete(@PathVariable("id") String id) {

        sysProcessInstanceCopyService.delete(id);

        return RestResponse.success();
    }
}

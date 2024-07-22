package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.OpenProcessInstanceNodeRecord;
import com.spyker.commons.search.OpenProcessInstanceNodeRecordSearch;
import com.spyker.commons.service.OpenProcessInstanceNodeRecordService;
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
 * 流程节点记录 前端控制器
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Tag(name = "流程节点记录", description = "流程节点记录")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/open-process-instance-node-record")
@Slf4j
public class OpenProcessInstanceNodeRecordController extends BaseController {

    // @formatter:off

    private final OpenProcessInstanceNodeRecordService openProcessInstanceNodeRecordService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "流程节点记录--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<OpenProcessInstanceNodeRecord>> list(
            OpenProcessInstanceNodeRecordSearch search) {

        List<OpenProcessInstanceNodeRecord> result =
                openProcessInstanceNodeRecordService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "流程节点记录--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<OpenProcessInstanceNodeRecord>> listPage(
            @ModelAttribute OpenProcessInstanceNodeRecordSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<OpenProcessInstanceNodeRecord> page = new Page<>(current, size);

        page = openProcessInstanceNodeRecordService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "流程节点记录--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<OpenProcessInstanceNodeRecord> detail(@PathVariable("id") String id) {

        OpenProcessInstanceNodeRecord result = openProcessInstanceNodeRecordService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "流程节点记录--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<OpenProcessInstanceNodeRecord> add(
            @RequestBody OpenProcessInstanceNodeRecord add) {

        openProcessInstanceNodeRecordService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "流程节点记录--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<OpenProcessInstanceNodeRecord> update(
            @PathVariable("id") String id, @RequestBody OpenProcessInstanceNodeRecord update) {

        update.setId(id);

        openProcessInstanceNodeRecordService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "流程节点记录--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<OpenProcessInstanceNodeRecord> delete(@PathVariable("id") String id) {

        openProcessInstanceNodeRecordService.delete(id);

        return RestResponse.success();
    }
}
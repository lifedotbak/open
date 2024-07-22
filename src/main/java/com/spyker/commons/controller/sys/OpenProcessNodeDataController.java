package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.OpenProcessNodeData;
import com.spyker.commons.search.OpenProcessNodeDataSearch;
import com.spyker.commons.service.OpenProcessNodeDataService;
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
 * 流程节点数据 前端控制器
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Tag(name = "流程节点数据", description = "流程节点数据")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/open-process-node-data")
@Slf4j
public class OpenProcessNodeDataController extends BaseController {

    // @formatter:off

    private final OpenProcessNodeDataService openProcessNodeDataService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "流程节点数据--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<OpenProcessNodeData>> list(OpenProcessNodeDataSearch search) {

        List<OpenProcessNodeData> result = openProcessNodeDataService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "流程节点数据--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<OpenProcessNodeData>> listPage(
            @ModelAttribute OpenProcessNodeDataSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<OpenProcessNodeData> page = new Page<>(current, size);

        page = openProcessNodeDataService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "流程节点数据--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<OpenProcessNodeData> detail(@PathVariable("id") String id) {

        OpenProcessNodeData result = openProcessNodeDataService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "流程节点数据--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<OpenProcessNodeData> add(@RequestBody OpenProcessNodeData add) {

        openProcessNodeDataService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "流程节点数据--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<OpenProcessNodeData> update(
            @PathVariable("id") String id, @RequestBody OpenProcessNodeData update) {

        update.setId(id);

        openProcessNodeDataService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "流程节点数据--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<OpenProcessNodeData> delete(@PathVariable("id") String id) {

        openProcessNodeDataService.delete(id);

        return RestResponse.success();
    }
}
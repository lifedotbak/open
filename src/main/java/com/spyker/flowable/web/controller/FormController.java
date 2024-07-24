package com.spyker.flowable.web.controller;

import com.spyker.flowable.biz.service.IFormService;
import com.spyker.flowable.biz.vo.FormRemoteSelectOptionParamVo;
import com.spyker.flowable.biz.vo.QueryFormListParamVo;
import com.spyker.flowable.common.dto.R;
import com.spyker.flowable.common.dto.flow.FormItemVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

/** 表单相关控制器 */
@Tag(name = "表单相关控制器", description = "表单相关控制器")
@RestController
@RequestMapping(value = "form")
public class FormController {
    @Resource private IFormService formService;

    /**
     * 获取下拉选项
     *
     * @return
     */
    @Operation(summary = "获取下拉选项", description = "获取下拉选项")
    @PostMapping("selectOptions")
    public R selectOptions(
            @RequestBody FormRemoteSelectOptionParamVo formRemoteSelectOptionParamVo) {

        return formService.selectOptions(formRemoteSelectOptionParamVo);
    }

    /**
     * 获取表单数据
     *
     * @param taskDto 参数
     * @return
     */
    @Operation(summary = "获取表单数据", description = "获取表单数据")
    @PostMapping("getFormList")
    public R<List<FormItemVO>> getFormList(@RequestBody QueryFormListParamVo taskDto) {
        return formService.getFormList(taskDto);
    }

    /**
     * 获取表单详细数据
     *
     * @param taskDto 参数
     * @return
     */
    @Operation(summary = "获取表单详细数据", description = "获取表单详细数据")
    @PostMapping("getFormDetail")
    public R getFormDetail(@RequestBody QueryFormListParamVo taskDto) {
        return formService.getFormDetail(taskDto);
    }

    /**
     * 动态表单
     *
     * @param taskDto
     * @return
     */
    @Operation(summary = "动态表单", description = "动态表单")
    @PostMapping("dynamicFormList")
    public R<List<FormItemVO>> dynamicFormList(@RequestBody QueryFormListParamVo taskDto) {
        return formService.dynamicFormList(taskDto);
    }
}
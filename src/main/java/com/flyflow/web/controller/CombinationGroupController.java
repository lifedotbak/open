package com.flyflow.web.controller;

import com.flyflow.biz.service.ICombinationGroupService;
import com.flyflow.biz.vo.FormGroupVo;
import com.flyflow.biz.vo.ProcessDataQueryVO;
import com.flyflow.common.dto.PageDto;
import com.flyflow.common.dto.R;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

/** 流程组聚合接口控制器 */
@Tag(name = "流程组聚合接口控制器", description = "流程组聚合接口控制器")
@RestController
@RequestMapping(value = "combination/group")
public class CombinationGroupController {

    @Resource private ICombinationGroupService combinationGroupService;

    /**
     * 查询表单组包含流程
     *
     * @param hidden 是否查询隐藏的
     * @return 表单组数据
     */
    @Operation(summary = "查询表单组包含流程", description = "查询表单组包含流程")
    @GetMapping("listGroupWithProcess")
    public R<List<FormGroupVo>> listGroupWithProcess(Boolean hidden) {
        return combinationGroupService.listGroupWithProcess(hidden);
    }

    /**
     * 查询流程分组和主流程数据
     *
     * @return
     */
    @Operation(summary = "查询流程分组和主流程数据", description = "查询流程分组和主流程数据")
    @GetMapping("listGroupWithProcessMain")
    public R<List<FormGroupVo>> listGroupWithProcessMain() {
        return combinationGroupService.listGroupWithProcessMain();
    }

    /**
     * 搜索流程
     *
     * @param word 关键词
     * @return
     */
    @Operation(summary = "搜索流程", description = "搜索流程")
    @GetMapping("searchFlowList")
    public R<List<FormGroupVo.FlowVo>> searchFlowList(String word) {
        return combinationGroupService.searchFlowList(word);
    }

    /**
     * 查询所有我可以发起的表单组
     *
     * @return
     */
    @Operation(summary = "查询所有我可以发起的表单组", description = "查询所有我可以发起的表单组")
    @GetMapping("listCurrentUserStartGroup")
    public R<List<FormGroupVo>> listCurrentUserStartGroup() {
        return combinationGroupService.listCurrentUserStartGroup();
    }

    /**
     * 删除主流程
     *
     * @param uniqueId
     * @return
     */
    @Parameter(name = "uniqueId", description = "", in = ParameterIn.PATH, required = true)
    @Operation(summary = "删除主流程", description = "删除主流程")
    @DeleteMapping("deleteProcessMain/{uniqueId}")
    public R deleteProcessMain(@PathVariable String uniqueId) {
        return combinationGroupService.deleteProcessMain(uniqueId);
    }

    /**
     * 清理流程
     *
     * @param uniqueId 流程唯一id
     * @return 成功失败
     */
    @Parameter(name = "uniqueId", description = "流程唯一id", in = ParameterIn.PATH, required = true)
    @Operation(summary = "清理流程", description = "清理流程")
    @DeleteMapping("clear/{uniqueId}")
    public R clear(@PathVariable String uniqueId) {
        return combinationGroupService.clear(uniqueId);
    }

    /**
     * 查询当前登录用户的待办任务
     *
     * @param pageVO
     * @return
     */
    @Operation(summary = "查询当前登录用户的待办任务", description = "查询当前登录用户的待办任务")
    @PostMapping("queryTodoTaskList")
    public R queryTodoTaskList(@RequestBody PageDto pageVO) {
        return combinationGroupService.queryTodoTaskList(pageVO);
    }

    /**
     * 查询已办任务
     *
     * @param pageVO
     * @return
     */
    @Operation(summary = "查询已办任务", description = "查询已办任务")
    @PostMapping("queryFinishedTaskList")
    public R queryFinishedTaskList(@RequestBody ProcessDataQueryVO pageVO) {
        return combinationGroupService.queryFinishedTaskList(pageVO);
    }

    /**
     * 查询已发起的任务
     *
     * @param pageVO
     * @return
     */
    @Operation(summary = "查询已发起的任务", description = "查询已发起的任务")
    @PostMapping("queryInitiatedTaskList")
    public R queryInitiatedTaskList(@RequestBody ProcessDataQueryVO pageVO) {
        return combinationGroupService.queryInitiatedTaskList(pageVO);
    }

    /**
     * 查询抄送任务
     *
     * @param pageVO
     * @return
     */
    @Operation(summary = "查询抄送任务", description = "查询抄送任务")
    @PostMapping("queryCopiedTaskList")
    public R queryCopiedTaskList(@RequestBody ProcessDataQueryVO pageVO) {
        return combinationGroupService.queryCopiedTaskList(pageVO);
    }
}
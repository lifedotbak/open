package com.spyker.flowable.controller;

import com.spyker.commons.satoken.SaTokenUtils;
import com.spyker.flowable.entity.TableDataInfo;
import com.spyker.flowable.entity.TaskInfo;
import com.spyker.framework.util.text.ExStringUtils;
import com.spyker.framework.web.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.annotation.Resource;
import org.flowable.engine.FormService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Tag(name = "待办任务接口")
@Controller
@RequestMapping("/task/manage")
public class TaskController {

    private final String prefix = "flowable/task";
    @Autowired FormService formService;
    @Autowired private RuntimeService runtimeService;
    @Autowired private TaskService taskService;
    @Resource
    private HistoryService historyService;

    @GetMapping("/mytask")
    public String mytasks() {
        return prefix + "/mytasks";
    }

    @GetMapping("/alltasks")
    public String alltasks() {
        return prefix + "/alltasks";
    }

    /** 查询我的待办任务列表 */
    @Operation(summary = "查询我的待办任务列表", description = "查询我的待办任务列表")
    @PostMapping("/mylist")
    @ResponseBody
    public TableDataInfo mylist(TaskInfo param) {

        String username = SaTokenUtils.getLoginUserName();

        TaskQuery condition = taskService.createTaskQuery().taskAssignee(username);
        if (ExStringUtils.isNotEmpty(param.getTaskName())) {
            condition.taskName(param.getTaskName());
        }
        if (ExStringUtils.isNotEmpty(param.getProcessName())) {
            condition.processDefinitionName(param.getProcessName());
        }
        // 过滤掉流程挂起的待办任务
        int total = condition.active().orderByTaskCreateTime().desc().list().size();
        int start = (param.getPageNum() - 1) * param.getPageSize();
        List<Task> taskList =
                condition
                        .active()
                        .orderByTaskCreateTime()
                        .desc()
                        .listPage(start, param.getPageSize());
        List<TaskInfo> tasks = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        taskList.stream()
                .forEach(
                        a -> {
                            ProcessInstance process =
                                    runtimeService
                                            .createProcessInstanceQuery()
                                            .processInstanceId(a.getProcessInstanceId())
                                            .singleResult();
                            TaskInfo info = new TaskInfo();
                            info.setAssignee(a.getAssignee());
                            info.setBusinessKey(process.getBusinessKey());
                            info.setCreateTime(sdf.format(a.getCreateTime()));
                            info.setTaskName(a.getName());
                            info.setExecutionId(a.getExecutionId());
                            info.setProcessInstanceId(a.getProcessInstanceId());
                            info.setProcessName(process.getProcessDefinitionName());
                            info.setStarter(process.getStartUserId());
                            info.setStartTime(sdf.format(process.getStartTime()));
                            info.setTaskId(a.getId());
                            String formKey = formService.getTaskFormData(a.getId()).getFormKey();
                            info.setFormKey(formKey);
                            tasks.add(info);
                        });
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(tasks);
        rspData.setTotal(total);
        return rspData;
    }

    /** 查询所有待办任务列表 */
    @Operation(summary = "查询所有待办任务列表", description = "查询所有待办任务列表")
    @PostMapping("/alllist")
    @ResponseBody
    public TableDataInfo alllist(TaskInfo param) {
        TaskQuery condition = taskService.createTaskQuery();
        if (ExStringUtils.isNotEmpty(param.getTaskName())) {
            condition.taskName(param.getTaskName());
        }
        if (ExStringUtils.isNotEmpty(param.getProcessName())) {
            condition.processDefinitionName(param.getProcessName());
        }
        int total = condition.active().orderByTaskCreateTime().desc().list().size();
        int start = (param.getPageNum() - 1) * param.getPageSize();
        List<Task> taskList =
                condition
                        .active()
                        .orderByTaskCreateTime()
                        .desc()
                        .listPage(start, param.getPageSize());
        List<TaskInfo> tasks = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        taskList.stream()
                .forEach(
                        a -> {
                            ProcessInstance process =
                                    runtimeService
                                            .createProcessInstanceQuery()
                                            .processInstanceId(a.getProcessInstanceId())
                                            .singleResult();
                            TaskInfo info = new TaskInfo();
                            info.setAssignee(a.getAssignee());
                            info.setBusinessKey(process.getBusinessKey());
                            info.setCreateTime(sdf.format(a.getCreateTime()));
                            info.setTaskName(a.getName());
                            info.setExecutionId(a.getExecutionId());
                            info.setProcessInstanceId(a.getProcessInstanceId());
                            info.setProcessName(process.getProcessDefinitionName());
                            info.setStarter(process.getStartUserId());
                            info.setStartTime(sdf.format(process.getStartTime()));
                            info.setTaskId(a.getId());
                            String formKey = formService.getTaskFormData(a.getId()).getFormKey();
                            info.setFormKey(formKey);
                            tasks.add(info);
                        });
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(tasks);
        rspData.setTotal(total);
        return rspData;
    }

    /** 用taskid查询formkey */
    @Operation(summary = "用taskid查询formkey", description = "用taskid查询formkey")
    @PostMapping("/forminfo/{taskId}")
    @ResponseBody
    public RestResponse alllist(@PathVariable String taskId) {
        String formKey = formService.getTaskFormData(taskId).getFormKey();
        return RestResponse.success(formKey);
    }

    @Operation(summary = "办理一个用户任务", description = "办理一个用户任务")
    @RequestMapping(value = "/completeTask/{taskId}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse completeTask(
            @PathVariable("taskId") String taskId,
            @RequestBody(required = false) Map<String, Object> variables) {

        String username = SaTokenUtils.getLoginUserName();

        taskService.setAssignee(taskId, username);
        // 查出流程实例id
        String processInstanceId =
                taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
        if (variables == null) {
            taskService.complete(taskId);
        } else {
            // 添加审批意见
            if (variables.get("comment") != null) {
                taskService.addComment(
                        taskId, processInstanceId, (String) variables.get("comment"));
                variables.remove("comment");
            }
            taskService.complete(taskId, variables);
        }
        return RestResponse.success();
    }

    @Operation(summary = "任务办理时间轴", description = "任务办理时间轴")
    @RequestMapping(value = "/history/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    public List<TaskInfo> history(@PathVariable String taskId) {
        String processInstanceId =
                taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
        List<HistoricActivityInstance> history =
                historyService
                        .createHistoricActivityInstanceQuery()
                        .processInstanceId(processInstanceId)
                        .activityType("userTask")
                        .orderByHistoricActivityInstanceStartTime()
                        .asc()
                        .list();
        List<TaskInfo> infos = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        history.stream()
                .forEach(
                        h -> {
                            TaskInfo info = new TaskInfo();
                            info.setProcessInstanceId(h.getProcessInstanceId());
                            info.setStartTime(sdf.format(h.getStartTime()));
                            if (h.getEndTime() != null) {
                                info.setEndTime(sdf.format(h.getEndTime()));
                            }
                            info.setAssignee(h.getAssignee());
                            info.setTaskName(h.getActivityName());
                            List<Comment> comments = taskService.getTaskComments(h.getTaskId());
                            if (comments.size() > 0) {
                                info.setComment(comments.get(0).getFullMessage());
                            }
                            infos.add(info);
                        });
        return infos;
    }
}
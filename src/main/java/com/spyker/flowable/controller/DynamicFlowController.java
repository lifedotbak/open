package com.spyker.flowable.controller;

import com.spyker.framework.util.text.ExStringUtils;
import com.spyker.framework.web.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.flowable.bpmn.BpmnAutoLayout;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.Execution;
import org.flowable.task.api.Task;
import org.flowable.validation.ValidationError;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

@Tag(name = "动态流程接口", description = "动态流程接口")
@Controller
@RequestMapping("/dynamic/flow")
public class DynamicFlowController {

    @Resource RepositoryService repositoryService;
    @Resource private RuntimeService runtimeService;
    @Resource private TaskService taskService;
    @Resource private HistoryService historyService;

    @Operation(summary = "遍历流程信息", description = "遍历流程信息")
    @GetMapping(value = "/info/{processInstanceId}")
    @ResponseBody
    public RestResponse remove(@PathVariable String processInstanceId) {
        String processDefinitionId =
                runtimeService
                        .createProcessInstanceQuery()
                        .processInstanceId(processInstanceId)
                        .singleResult()
                        .getProcessDefinitionId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
        for (FlowElement flowElement : flowElements) {
            if (flowElement instanceof UserTask userTask) {
                System.out.println(flowElement.getName());
                System.out.println(flowElement.getId());
                System.out.println(userTask.getAssignee());
                String assigneeEl = userTask.getAssignee();
                if (ExStringUtils.isBlank(assigneeEl)) {
                    continue;
                }
                if (assigneeEl.startsWith("${")
                        && assigneeEl.endsWith("}")
                        && assigneeEl.length() > 3) {
                    String assignee = assigneeEl.substring(2, assigneeEl.length() - 2);
                    System.out.println("assignee:" + assignee);
                }
            }
        }
        return RestResponse.success(flowElements);
    }

    @Operation(summary = "撤销:强制结束一个流程", description = "撤销:强制结束一个流程")
    @GetMapping(value = "/forceEnd/{taskId}")
    @ResponseBody
    public RestResponse forceEnd(@PathVariable String taskId) {
        Task t = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processDefinitionId =
                runtimeService
                        .createProcessInstanceQuery()
                        .processInstanceId(t.getProcessInstanceId())
                        .singleResult()
                        .getProcessDefinitionId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        // 寻找流程实例当前任务的activeId
        Execution execution =
                runtimeService
                        .createExecutionQuery()
                        .executionId(t.getExecutionId())
                        .singleResult();
        String activityId = execution.getActivityId();
        FlowNode currentNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityId);
        // 创建结束节点和连接线
        EndEvent end = new EndEvent();
        end.setName("强制结束");
        end.setId("forceEnd");
        List<SequenceFlow> newSequenceFlowList = new ArrayList<SequenceFlow>();
        SequenceFlow newSequenceFlow = new SequenceFlow();
        newSequenceFlow.setId("newFlow");
        newSequenceFlow.setSourceFlowElement(currentNode);
        newSequenceFlow.setTargetFlowElement(end);
        newSequenceFlowList.add(newSequenceFlow);
        // 备份原有方向
        List<SequenceFlow> dataflows = currentNode.getOutgoingFlows();
        List<SequenceFlow> oriSequenceFlows = new ArrayList<SequenceFlow>();
        oriSequenceFlows.addAll(dataflows);
        // 清空原有方向
        currentNode.getOutgoingFlows().clear();
        // 设置新方向
        currentNode.setOutgoingFlows(newSequenceFlowList);
        // 完成当前任务
        taskService.addComment(taskId, t.getProcessInstanceId(), "comment", "撤销流程");
        taskService.complete(taskId);
        // 恢复原有方向
        currentNode.setOutgoingFlows(oriSequenceFlows);
        return RestResponse.success();
    }

    @Operation(summary = "驳回或跳转到指定节点", description = "驳回或跳转到指定节点")
    @GetMapping(value = "/jump/{taskId}/{sid}")
    @ResponseBody
    public RestResponse jump(@PathVariable String taskId, @PathVariable String sid) {
        Task t = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processDefinitionId =
                runtimeService
                        .createProcessInstanceQuery()
                        .processInstanceId(t.getProcessInstanceId())
                        .singleResult()
                        .getProcessDefinitionId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        // 寻找流程实例当前任务的activeId
        Execution execution =
                runtimeService
                        .createExecutionQuery()
                        .executionId(t.getExecutionId())
                        .singleResult();
        String activityId = execution.getActivityId();
        FlowNode currentNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityId);
        FlowNode targetNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(sid);
        // 创建连接线
        List<SequenceFlow> newSequenceFlowList = new ArrayList<SequenceFlow>();
        SequenceFlow newSequenceFlow = new SequenceFlow();
        newSequenceFlow.setId("newFlow");
        newSequenceFlow.setSourceFlowElement(currentNode);
        newSequenceFlow.setTargetFlowElement(targetNode);
        newSequenceFlowList.add(newSequenceFlow);
        // 备份原有方向
        List<SequenceFlow> dataflows = currentNode.getOutgoingFlows();
        List<SequenceFlow> oriSequenceFlows = new ArrayList<SequenceFlow>();
        oriSequenceFlows.addAll(dataflows);
        // 清空原有方向
        currentNode.getOutgoingFlows().clear();
        // 设置新方向
        currentNode.setOutgoingFlows(newSequenceFlowList);
        // 完成当前任务
        taskService.addComment(taskId, t.getProcessInstanceId(), "comment", "跳转节点");
        taskService.complete(taskId);
        // 恢复原有方向
        currentNode.setOutgoingFlows(oriSequenceFlows);
        return RestResponse.success();
    }

    @Operation(summary = "动态创建流程", description = "动态创建流程")
    @GetMapping(value = "/createProcess")
    @ResponseBody
    public RestResponse createProcess() {
        // 开始节点的属性
        StartEvent startEvent = new StartEvent();
        startEvent.setId("start");
        startEvent.setName("start");
        // 普通UserTask节点
        UserTask userTask = new UserTask();
        userTask.setId("userTask");
        userTask.setName("审批任务");
        // 结束节点属性
        EndEvent endEvent = new EndEvent();
        endEvent.setId("end");
        endEvent.setName("end");
        // 连线信息
        List<SequenceFlow> flows = new ArrayList<SequenceFlow>();
        List<SequenceFlow> toEnd = new ArrayList<SequenceFlow>();
        SequenceFlow s1 = new SequenceFlow();
        s1.setId("flow1");
        s1.setName("flow1");
        s1.setSourceRef(startEvent.getId());
        s1.setTargetRef(userTask.getId());
        flows.add(s1);

        SequenceFlow s2 = new SequenceFlow();
        s2.setId("flow2");
        s2.setName("flow2");
        s2.setSourceRef(userTask.getId());
        s2.setTargetRef(endEvent.getId());
        toEnd.add(s2);
        startEvent.setOutgoingFlows(flows);
        userTask.setOutgoingFlows(toEnd);

        // 给流程对象添加元素
        Process process = new Process();
        process.setId("dynamicProcess");
        process.setName("动态流程");
        process.addFlowElement(startEvent);
        process.addFlowElement(s1);
        process.addFlowElement(userTask);
        process.addFlowElement(s2);
        process.addFlowElement(endEvent);
        // 创建模型对象
        BpmnModel bpmnModel = new BpmnModel();
        bpmnModel.addProcess(process);
        // 流程图自动布局
        new BpmnAutoLayout(bpmnModel).execute();

        // 模型合法性校验
        List<ValidationError> validationErrorList = repositoryService.validateProcess(bpmnModel);
        if (validationErrorList.size() == 0) {
            // 模型合法就部署流程
            Deployment deploy =
                    repositoryService
                            .createDeployment()
                            .category("dynamic")
                            .key("dynamicProcess")
                            .addBpmnModel("dynamicProcess.bpmn20.xml", bpmnModel)
                            .deploy();
            return RestResponse.success("success");
        } else {
            return RestResponse.error(-1, "fail");
        }
    }
}
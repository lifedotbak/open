package cc.flyflow.core.service.impl;

import cc.flyflow.common.dto.IndexPageStatistics;
import cc.flyflow.common.dto.ProcessInstanceParamDto;
import cc.flyflow.common.dto.R;
import cc.flyflow.common.dto.VariableQueryParamDto;
import cc.flyflow.common.service.core.IProcessInstanceService;
import cc.flyflow.common.utils.TenantUtil;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstanceQuery;
import org.flowable.task.api.TaskQuery;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author Huijun Zhao
 * @description
 * @date 2023-11-08 17:30
 */
@Component("coreProcessInstanceService")
public class ProcessInstanceServiceImpl implements IProcessInstanceService {

    @Resource private RuntimeService runtimeService;
    @Resource private TaskService taskService;

    @Resource private HistoryService historyService;

    /**
     * 删除流程
     *
     * @param processInstanceParamDto
     * @return
     */
    @Override
    public R delete(ProcessInstanceParamDto processInstanceParamDto) {

        List<String> processInstanceIdList = processInstanceParamDto.getProcessInstanceIdList();
        for (String processInstanceId : processInstanceIdList) {
            if (runtimeService
                            .createProcessInstanceQuery()
                            .processInstanceTenantId(TenantUtil.get())
                            .processInstanceId(processInstanceId)
                            .count()
                    > 0) {
                runtimeService.deleteProcessInstance(
                        processInstanceId, processInstanceParamDto.getReason());
            }
        }

        return R.success();
    }

    /**
     * 查询统计数据
     *
     * @param userId
     * @return
     */
    @Override
    public R<IndexPageStatistics> querySimpleData(String userId) {
        String tenantId = TenantUtil.get();
        TaskQuery taskQuery = taskService.createTaskQuery().taskTenantId(tenantId);

        // 待办数量
        long pendingNum = taskQuery.taskAssignee((userId)).count();
        // 已完成任务
        HistoricActivityInstanceQuery historicActivityInstanceQuery =
                historyService.createHistoricActivityInstanceQuery().activityTenantId(tenantId);

        long completedNum =
                historicActivityInstanceQuery
                        .taskAssignee(String.valueOf(userId))
                        .finished()
                        .count();

        IndexPageStatistics indexPageStatistics =
                IndexPageStatistics.builder()
                        .pendingNum(pendingNum)
                        .completedNum(completedNum)
                        .build();

        return R.success(indexPageStatistics);
    }

    /**
     * 查询变量
     *
     * @param paramDto
     * @return
     */
    @Override
    public R<Map<String, Object>> queryVariables(VariableQueryParamDto paramDto) {
        long count =
                runtimeService
                        .createProcessInstanceQuery()
                        .processInstanceTenantId(TenantUtil.get())
                        .processInstanceId(paramDto.getExecutionId())
                        .count();
        if (count > 0) {

            Map<String, Object> variables = runtimeService.getVariables(paramDto.getExecutionId());

            return R.success(variables);
        }

        List<HistoricVariableInstance> list =
                historyService
                        .createHistoricVariableInstanceQuery()
                        .processInstanceId(paramDto.getExecutionId())
                        .list();

        Map<String, Object> variables = new HashMap<>();
        for (HistoricVariableInstance historicVariableInstance : list) {
            variables.put(
                    historicVariableInstance.getVariableName(),
                    historicVariableInstance.getValue());
        }

        return R.success(variables);
    }
}
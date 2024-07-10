package com.flyflow.biz.service.impl;

import com.flyflow.biz.api.ApiStrategyFactory;
import com.flyflow.biz.entity.ProcessInstanceRecord;
import com.flyflow.biz.service.*;
import com.flyflow.biz.utils.CoreHttpUtil;
import com.flyflow.common.constants.OperTypeEnum;
import com.flyflow.common.constants.ProcessInstanceConstant;
import com.flyflow.common.dto.R;
import com.flyflow.common.dto.TaskParamDto;
import com.flyflow.common.dto.TaskResultDto;
import com.flyflow.common.dto.flow.Node;
import com.flyflow.common.dto.third.UserDto;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Slf4j
public class TaskServiceImpl implements ITaskService {
    @Resource private IFileService fileService;

    @Resource private IProcessService processService;
    @Resource private IProcessInstanceNodeRecordService processInstanceNodeRecordService;
    @Resource private IProcessNodeDataService nodeDataService;
    @Resource private IProcessInstanceAssignUserRecordService processNodeRecordAssignUserService;
    @Resource private IProcessInstanceRecordService processInstanceRecordService;

    @Resource
    private IProcessInstanceAssignUserRecordService processInstanceAssignUserRecordService;

    @Resource private IProcessInstanceOperRecordService processInstanceOperRecordService;

    @Resource private RedisTemplate redisTemplate;

    /**
     * 完成任务
     *
     * @param taskParamDto
     * @return
     */
    @Transactional
    @Override
    public R completeTask(TaskParamDto taskParamDto) {
        String userId = StpUtil.getLoginIdAsString();
        taskParamDto.setUserId(String.valueOf(userId));
        UserDto userDto = ApiStrategyFactory.getStrategy().getUser(userId);
        taskParamDto.setUserName(userDto.getName());

        // 判断任务是否是合同 如果是合同 不能提交
        R<TaskResultDto> re = CoreHttpUtil.queryTask(taskParamDto.getTaskId(), userId);
        if (!re.isOk()) {
            return R.fail(re.getMsg());
        }
        TaskResultDto taskResultDto = re.getData();

        Map<String, Object> paramMap = taskParamDto.getParamMap();

        R r = CoreHttpUtil.completeTask(taskParamDto);

        if (!r.isOk()) {
            return R.fail(r.getMsg());
        }

        // 完成任务了 判断如果没有发起人部门id 修改主部门id 主要是子流程的问题
        String mainDeptId =
                MapUtil.getStr(
                        paramMap, ProcessInstanceConstant.VariableKey.START_USER_MAIN_DEPTID_KEY);
        if (StrUtil.isNotBlank(mainDeptId)) {
            ProcessInstanceRecord processInstanceRecord =
                    processInstanceRecordService.getByProcessInstanceId(
                            taskResultDto.getProcessInstanceId());
            String mainDeptIdDb = processInstanceRecord.getMainDeptId();
            if (StrUtil.isBlank(mainDeptIdDb)) {
                processInstanceRecordService
                        .lambdaUpdate()
                        .eq(
                                ProcessInstanceRecord::getProcessInstanceId,
                                taskResultDto.getProcessInstanceId())
                        .set(ProcessInstanceRecord::getMainDeptId, mainDeptId)
                        .update(new ProcessInstanceRecord());
            }
        }

        if (taskParamDto.getApproveResult()) {
            processInstanceOperRecordService.saveRecord(
                    userId, taskParamDto, OperTypeEnum.PASS.getValue(), "提交任务");
        } else {
            processInstanceOperRecordService.saveRecord(
                    userId, taskParamDto, OperTypeEnum.REFUSE.getValue(), "提交任务");
        }

        return R.success();
    }

    /**
     * 获取任务信息
     *
     * @param taskId 任务id
     * @return
     */
    @Override
    public R getTask(String taskId) {
        String userId = StpUtil.getLoginIdAsString();
        R<TaskResultDto> r = CoreHttpUtil.queryTask(taskId, userId);
        if (!r.isOk()) {
            return R.fail(r.getMsg());
        }
        TaskResultDto taskResultDto = r.getData();
        String flowId = taskResultDto.getFlowId();
        String nodeId = taskResultDto.getNodeId();

        Node node = nodeDataService.getNode(flowId, nodeId).getData();
        Dict set =
                Dict.create()
                        .set("nodeType", node.getType())
                        .set("processInstanceId", taskResultDto.getProcessInstanceId())
                        .set("flowId", taskResultDto.getFlowId())
                        .set("currentTask", taskResultDto.getCurrentTask());

        return R.success(set);
    }
}
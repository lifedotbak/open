package cc.flyflow.biz.service.impl;

import cc.flyflow.biz.api.ApiStrategyFactory;
import cc.flyflow.biz.constants.ProcessInstanceAssignUserRecordStatusEnum;
import cc.flyflow.biz.constants.ProcessInstanceNodeRecordStatusEnum;
import cc.flyflow.biz.constants.ProcessInstanceRecordStatusEnum;
import cc.flyflow.biz.entity.*;
import cc.flyflow.biz.entity.Process;
import cc.flyflow.biz.service.*;
import cc.flyflow.biz.utils.DataUtil;
import cc.flyflow.common.constants.MessageTypeEnum;
import cc.flyflow.common.constants.TaskTypeEnum;
import cc.flyflow.common.dto.*;
import cc.flyflow.common.dto.ProcessInstanceParamDto;
import cc.flyflow.common.dto.flow.Node;
import cc.flyflow.common.dto.third.*;
import cc.flyflow.common.dto.third.MessageDto;
import cc.flyflow.common.service.biz.IRemoteService;
import cc.flyflow.common.utils.JsonUtil;
import cc.flyflow.common.utils.NodeUtil;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

@Service
@Slf4j
public class RemoteServiceImpl implements IRemoteService {

    @Resource private IProcessInstanceRecordService processInstanceRecordService;
    @Resource private IProcessInstanceNodeRecordService processNodeRecordService;
    @Resource private IProcessInstanceAssignUserRecordService processNodeRecordAssignUserService;
    @Resource private IProcessInstanceService processInstanceService;
    @Resource private IProcessInstanceCopyService processCopyService;
    @Resource private IProcessInstanceUserCopyService processInstanceUserCopyService;
    @Resource private IProcessService processService;
    @Resource private IProcessGroupService processGroupService;

    @Resource private RedisTemplate redisTemplate;
    @Resource private IProcessNodeDataService processNodeDataService;
    @Resource private IMessageService messageService;

    @Resource private IProcessInstanceExecutionService processInstanceExecutionService;

    /**
     * 根据用户id查询角色id集合
     *
     * @param userId
     * @return
     */
    @Override
    public R<List<String>> loadRoleIdListByUserId(String userId) {
        List<String> list = ApiStrategyFactory.getStrategy().loadRoleIdListByUserId(userId);
        return R.success(list);
    }

    /**
     * 根据部门id获取部门列表
     *
     * @param deptIdList
     * @return
     */
    @Override
    public R<List<DeptDto>> queryDeptList(List<String> deptIdList) {
        List<DeptDto> allDept = ApiStrategyFactory.getStrategy().loadAllDept(null);
        List<DeptDto> collect =
                allDept.stream()
                        .filter(w -> deptIdList.contains(w.getId()))
                        .collect(Collectors.toList());
        return R.success(collect);
    }

    /**
     * 保存待办任务
     *
     * @param messageDto
     * @return
     */
    @Override
    public R saveMessage(MessageDto messageDto) {

        ProcessInstanceRecord processInstanceRecord =
                processInstanceRecordService
                        .lambdaQuery()
                        .eq(
                                ProcessInstanceRecord::getProcessInstanceId,
                                messageDto.getProcessInstanceId())
                        .eq(ProcessInstanceRecord::getTenantId, messageDto.getTenantId())
                        .one();

        String userId = processInstanceRecord.getUserId();
        UserDto userDto = ApiStrategyFactory.getStrategy().getUser(userId);

        if (StrUtil.equals(MessageTypeEnum.TODO_TASK.getType(), messageDto.getType())) {

            messageDto.setTitle(
                    StrUtil.format(
                            "[{}]提交的“{}”待您处理", userDto.getName(), processInstanceRecord.getName()));
            messageDto.setContent(
                    StrUtil.format(
                            "[{}]提交的任务[{}]需要您来处理，请及时查看",
                            userDto.getName(),
                            processInstanceRecord.getName()));
        }

        messageDto.setStartUserId(userId);
        messageDto.setStartUserName(userDto.getName());
        messageDto.setFlowName(processInstanceRecord.getName());
        messageDto.setGroupName(processInstanceRecord.getGroupName());
        messageService.saveMessage(messageDto);

        ApiStrategyFactory.getStrategy().sendMsg(messageDto);
        return R.success();
    }

    /**
     * 根据角色id集合查询用户id集合
     *
     * @param roleIdList
     * @return
     */
    @Override
    public R<List<String>> queryUserIdListByRoleIdList(List<String> roleIdList) {

        UserQueryDto userQueryDto = UserQueryDto.builder().roleIdList(roleIdList).build();

        List<String> userIdList =
                ApiStrategyFactory.getStrategy()
                        .queryUserIdListByRoleIdListAndDeptIdList(userQueryDto);

        return R.success(userIdList);
    }

    /**
     * 根据用户id查询上级部门列表
     *
     * @param userQueryDto
     * @return
     */
    @Override
    public R<List<String>> queryUserIdListByRoleIdListAndDeptIdList(UserQueryDto userQueryDto) {

        List<String> list =
                ApiStrategyFactory.getStrategy()
                        .queryUserIdListByRoleIdListAndDeptIdList(userQueryDto);
        return R.success(list);
    }

    /**
     * 保存抄送
     *
     * @param copyDto
     * @return
     */
    @Transactional
    @Override
    public R saveCC(ProcessInstanceCopyDto copyDto) {
        String processInstanceId = copyDto.getProcessInstanceId();

        ProcessInstanceRecord processInstanceRecord =
                processInstanceRecordService
                        .lambdaQuery()
                        .eq(ProcessInstanceRecord::getProcessInstanceId, processInstanceId)
                        .one();

        // 批量保存抄送
        List<ProcessInstanceCopy> processInstanceCopyList = new ArrayList<>();
        for (String uid : copyDto.getUserIdList()) {
            ProcessInstanceCopy processInstanceCopy =
                    BeanUtil.copyProperties(copyDto, ProcessInstanceCopy.class);
            processInstanceCopy.setGroupId(processInstanceRecord.getGroupId());
            processInstanceCopy.setGroupName(processInstanceRecord.getGroupName());
            processInstanceCopy.setProcessName(processInstanceRecord.getName());
            processInstanceCopy.setStartTime(processInstanceRecord.getCreateTime());
            processInstanceCopy.setUserId(uid);
            processInstanceCopyList.add(processInstanceCopy);
        }
        processCopyService.saveBatch(processInstanceCopyList, 50);

        // 查询出当前流程实例中有过抄送的用户
        List<ProcessInstanceUserCopy> existUserCopyList =
                processInstanceUserCopyService
                        .lambdaQuery()
                        .in(ProcessInstanceUserCopy::getUserId, copyDto.getUserIdList())
                        .eq(
                                ProcessInstanceUserCopy::getProcessInstanceId,
                                copyDto.getProcessInstanceId())
                        .list();
        List<String> existUserIdList =
                existUserCopyList.stream()
                        .map(ProcessInstanceUserCopy::getUserId)
                        .collect(Collectors.toList());

        // 以流程为维度 插入用户是否收到抄送了
        List<ProcessInstanceUserCopy> processInstanceUserCopyList = new ArrayList<>();
        for (String s : copyDto.getUserIdList()) {
            if (existUserIdList.contains(s)) {
                continue;
            }
            ProcessInstanceUserCopy processInstanceUserCopy =
                    BeanUtil.copyProperties(copyDto, ProcessInstanceUserCopy.class);
            processInstanceUserCopy.setGroupId(processInstanceRecord.getGroupId());
            processInstanceUserCopy.setGroupName(processInstanceRecord.getGroupName());
            processInstanceUserCopy.setProcessName(processInstanceRecord.getName());
            processInstanceUserCopy.setUserId(s);
            processInstanceUserCopyList.add(processInstanceUserCopy);
        }
        if (CollUtil.isNotEmpty(processInstanceUserCopyList)) {
            processInstanceUserCopyService.saveBatch(processInstanceUserCopyList, 50);
        }

        return R.success();
    }

    /**
     * 检查是否是所有的父级
     *
     * @param checkParentDto
     * @return
     */
    @Override
    public R<Boolean> checkIsAllParent(CheckParentDto checkParentDto) {

        String parentId = checkParentDto.getParentId();
        List<String> deptIdList = checkParentDto.getDeptIdList();
        // 查询子级包括自己
        List<DeptDto> allDept = ApiStrategyFactory.getStrategy().loadAllDept(null);
        List<DeptDto> childrenDeptList = DataUtil.selectChildrenByDept(parentId, allDept);

        List<String> childrenDeptIdList =
                childrenDeptList.stream().map(w -> w.getId()).collect(Collectors.toList());
        childrenDeptIdList.remove(parentId);

        List<String> remainIdList =
                CollUtil.removeAny(deptIdList, ArrayUtil.toArray(childrenDeptIdList, String.class));

        return R.success(remainIdList.isEmpty());
    }

    /**
     * 根据部门id集合查询用户id集合
     *
     * @param deptIdList
     * @return
     */
    @Override
    public R<List<String>> queryUserIdListByDepIdList(List<String> deptIdList) {

        UserQueryDto userQueryDto = UserQueryDto.builder().deptIdList(deptIdList).build();

        List<String> userIdList =
                ApiStrategyFactory.getStrategy()
                        .queryUserIdListByRoleIdListAndDeptIdList(userQueryDto);

        return R.success(userIdList);
    }

    /**
     * 检查是否是所有的子级
     *
     * @param checkChildDto
     * @return
     */
    @Override
    public R<Boolean> checkIsAllChild(CheckChildDto checkChildDto) {
        String childId = checkChildDto.getChildId();
        List<String> deptIdList = checkChildDto.getDeptIdList();
        // 查询父级包括自己

        List<DeptDto> allDept = ApiStrategyFactory.getStrategy().loadAllDept(null);
        List<DeptDto> parentDeptList = DataUtil.selectParentByDept(childId, allDept);

        List<String> parentDeptIdList =
                parentDeptList.stream().map(w -> w.getId()).collect(Collectors.toList());
        parentDeptIdList.remove(childId);

        List<String> remainIdList =
                CollUtil.removeAny(deptIdList, ArrayUtil.toArray(parentDeptIdList, String.class));

        return R.success(remainIdList.isEmpty());
    }

    /**
     * 获取用户的信息-包括扩展字段
     *
     * @param userId
     * @return
     */
    @Override
    public R<Map<String, Object>> queryUserAllInfo(String userId) {

        UserDto user = ApiStrategyFactory.getStrategy().getUser(String.valueOf(userId));

        Map<String, Object> map = BeanUtil.beanToMap(user, "id", "name", "deptId", "parentId");

        return R.success(map);
    }

    /**
     * 查询上级部门
     *
     * @param deptId
     * @return
     */
    @Override
    public R<List<DeptDto>> queryParentDeptList(String deptId) {

        List<DeptDto> allDept = ApiStrategyFactory.getStrategy().loadAllDept(null);
        List<DeptDto> deptList = DataUtil.selectParentByDept(deptId, allDept);

        return R.success(deptList);
    }

    /**
     * 批量获取部门的下级部门
     *
     * @param deptIdList 部门id集合
     * @return
     */
    @Override
    public R<Map<String, List<DeptDto>>> batchQueryChildDeptList(List<String> deptIdList) {
        Map<String, List<DeptDto>> map = new HashMap<>();
        if (CollUtil.isEmpty(deptIdList)) {
            return R.success(map);
        }
        List<DeptDto> allDept = ApiStrategyFactory.getStrategy().loadAllDept(null);

        for (String deptId : deptIdList) {
            List<DeptDto> deptList = DataUtil.selectChildrenByDept(deptId, allDept);
            map.put(deptId, deptList);
        }

        return R.success(map);
    }

    /**
     * 开始节点事件
     *
     * @param recordParamDto
     * @return
     */
    @Override
    public R startNodeEvent(ProcessInstanceNodeRecordParamDto recordParamDto) {
        return processNodeRecordService.startNodeEvent(recordParamDto);
    }

    /**
     * 流程创建了
     *
     * @param processInstanceRecordParamDto
     * @return
     */
    @Override
    public R processStartEvent(ProcessInstanceRecordParamDto processInstanceRecordParamDto) {
        ProcessInstanceRecord entity =
                BeanUtil.copyProperties(processInstanceRecordParamDto, ProcessInstanceRecord.class);

        String flowId = processInstanceRecordParamDto.getFlowId();
        Process process = processService.getByFlowIdContainDeleted(flowId);
        String processInstanceId = processInstanceRecordParamDto.getProcessInstanceId();

        ProcessGroup processGroup = processGroupService.getById(process.getGroupId());

        entity.setName(process.getName());
        entity.setLogo(process.getLogo());
        entity.setUserId(processInstanceRecordParamDto.getUserId());
        entity.setFlowId(flowId);
        entity.setProcessInstanceId(processInstanceId);
        entity.setProcessInstanceBizKey(processInstanceRecordParamDto.getProcessInstanceBizKey());
        entity.setProcessInstanceBizCode(processInstanceRecordParamDto.getProcessInstanceBizCode());
        entity.setGroupId(processGroup.getId());
        entity.setGroupName(processGroup.getGroupName());
        entity.setStatus(ProcessInstanceRecordStatusEnum.JXZ.getCode());
        String processStr = process.getProcess();
        Node node = JsonUtil.parseObject(processStr, Node.class);
        NodeUtil.addEndNode(node);
        entity.setProcess(JsonUtil.toJSONString(node));

        processInstanceRecordService.save(entity);

        return R.success();
    }

    /**
     * 完成节点事件
     *
     * @param recordParamDto
     * @return
     */
    @Override
    public R endNodeEvent(ProcessInstanceNodeRecordParamDto recordParamDto) {

        // 处理任务
        ProcessInstanceNodeRecord processInstanceNodeRecord =
                processNodeRecordService
                        .lambdaQuery()
                        .eq(
                                ProcessInstanceNodeRecord::getProcessInstanceId,
                                recordParamDto.getProcessInstanceId())
                        .eq(
                                ProcessInstanceNodeRecord::getStatus,
                                ProcessInstanceNodeRecordStatusEnum.JXZ.getCode())
                        .eq(ProcessInstanceNodeRecord::getNodeId, recordParamDto.getNodeId())
                        .eq(ProcessInstanceNodeRecord::getTenantId, recordParamDto.getTenantId())
                        .eq(
                                ProcessInstanceNodeRecord::getExecutionId,
                                recordParamDto.getExecutionId())
                        .one();
        if (processInstanceNodeRecord != null) {

            {

                // 修改任务执行人员的状态
                processNodeRecordAssignUserService
                        .lambdaUpdate()
                        .set(
                                ProcessInstanceAssignUserRecord::getStatus,
                                ProcessInstanceAssignUserRecordStatusEnum.WCL.getCode())
                        .set(
                                ProcessInstanceAssignUserRecord::getTaskType,
                                TaskTypeEnum.CANCEL.getValue())
                        .eq(
                                ProcessInstanceAssignUserRecord::getProcessInstanceId,
                                recordParamDto.getProcessInstanceId())
                        .eq(
                                ProcessInstanceAssignUserRecord::getStatus,
                                ProcessInstanceAssignUserRecordStatusEnum.JXZ.getCode())
                        .eq(ProcessInstanceAssignUserRecord::getNodeId, recordParamDto.getNodeId())
                        .eq(
                                ProcessInstanceAssignUserRecord::getTenantId,
                                recordParamDto.getTenantId())
                        .eq(
                                ProcessInstanceAssignUserRecord::getParentExecutionId,
                                processInstanceNodeRecord.getExecutionId())
                        .update(new ProcessInstanceAssignUserRecord());
            }
        }
        return processNodeRecordService.endNodeEvent(recordParamDto);
    }

    /**
     * 节点取消
     *
     * @param recordParamDto
     * @return
     */
    @Transactional
    @Override
    public R cancelNodeEvent(ProcessInstanceNodeRecordParamDto recordParamDto) {

        // 处理任务
        List<ProcessInstanceNodeRecord> processInstanceNodeRecordList =
                processNodeRecordService
                        .lambdaQuery()
                        .eq(
                                ProcessInstanceNodeRecord::getProcessInstanceId,
                                recordParamDto.getProcessInstanceId())
                        .eq(
                                ProcessInstanceNodeRecord::getStatus,
                                ProcessInstanceNodeRecordStatusEnum.JXZ.getCode())
                        .eq(ProcessInstanceNodeRecord::getNodeId, recordParamDto.getNodeId())
                        .eq(ProcessInstanceNodeRecord::getTenantId, recordParamDto.getTenantId())
                        .eq(
                                ProcessInstanceNodeRecord::getExecutionId,
                                recordParamDto.getExecutionId())
                        .list();
        if (processInstanceNodeRecordList.size() > 1) {
            log.error("当前节点有多个进行中的，请查看:{}", JsonUtil.toJSONString(processInstanceNodeRecordList));
        }
        if (CollUtil.isNotEmpty(processInstanceNodeRecordList)) {
            Set<String> executionIdSet =
                    processInstanceNodeRecordList.stream()
                            .map(ProcessInstanceNodeRecord::getExecutionId)
                            .collect(Collectors.toSet());
            // 相关的用户任务置为已撤销
            processNodeRecordAssignUserService
                    .lambdaUpdate()
                    .set(
                            ProcessInstanceAssignUserRecord::getStatus,
                            ProcessInstanceAssignUserRecordStatusEnum.WCL.getCode())
                    .set(
                            ProcessInstanceAssignUserRecord::getTaskType,
                            TaskTypeEnum.CANCEL.getValue())
                    .eq(
                            ProcessInstanceAssignUserRecord::getProcessInstanceId,
                            recordParamDto.getProcessInstanceId())
                    .eq(
                            ProcessInstanceAssignUserRecord::getStatus,
                            ProcessInstanceAssignUserRecordStatusEnum.JXZ.getCode())
                    .eq(ProcessInstanceAssignUserRecord::getNodeId, recordParamDto.getNodeId())
                    .eq(ProcessInstanceAssignUserRecord::getTenantId, recordParamDto.getTenantId())
                    .in(ProcessInstanceAssignUserRecord::getParentExecutionId, executionIdSet)
                    .update(new ProcessInstanceAssignUserRecord());
        }

        processNodeRecordService.cancelNodeEvent(recordParamDto);

        return R.success();
    }

    /**
     * 开始设置执行人
     *
     * @param processInstanceAssignUserRecordParamDto
     * @return
     */
    @Override
    public R createTaskEvent(
            ProcessInstanceAssignUserRecordParamDto processInstanceAssignUserRecordParamDto) {
        return processNodeRecordAssignUserService.createTaskEvent(
                processInstanceAssignUserRecordParamDto);
    }

    /**
     * 任务结束事件
     *
     * @param processInstanceAssignUserRecordParamDto
     * @return
     */
    @Override
    public R taskCompletedEvent(
            ProcessInstanceAssignUserRecordParamDto processInstanceAssignUserRecordParamDto) {
        return processNodeRecordAssignUserService.taskCompletedEvent(
                processInstanceAssignUserRecordParamDto);
    }

    /**
     * 任务结束
     *
     * @param processInstanceAssignUserRecordParamDto
     * @return
     */
    @Override
    public R taskEndEvent(
            ProcessInstanceAssignUserRecordParamDto processInstanceAssignUserRecordParamDto) {
        return processNodeRecordAssignUserService.taskEndEvent(
                processInstanceAssignUserRecordParamDto);
    }

    /**
     * 实例结束
     *
     * @param processInstanceParamDto
     * @return
     */
    @Override
    public R processEndEvent(ProcessInstanceParamDto processInstanceParamDto) {
        return processInstanceService.processEndEvent(processInstanceParamDto);
    }

    /**
     * 查询流程管理员
     *
     * @param flowId
     * @return
     */
    @Override
    public R<String> queryProcessAdmin(String flowId) {
        Process process = processService.getByFlowId(flowId);
        return R.success(process.getAdminId());
    }

    /**
     * 查询流程数据
     *
     * @param flowId
     * @return
     */
    @Override
    public R<ProcessDto> queryProcess(String flowId) {
        Process process = processService.getByFlowId(flowId);

        return R.success(BeanUtil.copyProperties(process, ProcessDto.class));
    }

    /**
     * 保存流程节点数据
     *
     * @param processNodeDataDto
     * @return
     */
    @Override
    public R saveNodeData(ProcessNodeDataDto processNodeDataDto) {
        return processNodeDataService.saveNodeData(processNodeDataDto);
    }

    /***
     * 获取节点数据
     * @param flowId
     * @param nodeId
     * @return
     */
    @Override
    public R<String> getNodeData(String flowId, String nodeId) {
        return processNodeDataService.getNodeData(flowId, nodeId);
    }

    /**
     * 保存执行数据
     *
     * @param processInstanceExecutionDto
     * @return
     */
    @Override
    public R saveExecution(ProcessInstanceExecutionDto processInstanceExecutionDto) {

        ProcessInstanceExecution entity =
                BeanUtil.copyProperties(
                        processInstanceExecutionDto, ProcessInstanceExecution.class);

        processInstanceExecutionService.save(entity);

        return R.success();
    }
}
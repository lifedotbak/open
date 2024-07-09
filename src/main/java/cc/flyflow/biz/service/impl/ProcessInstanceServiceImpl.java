package cc.flyflow.biz.service.impl;

import cc.flyflow.biz.api.ApiStrategyFactory;
import cc.flyflow.biz.constants.ProcessInstanceRecordStatusEnum;
import cc.flyflow.biz.entity.Process;
import cc.flyflow.biz.entity.ProcessInstanceRecord;
import cc.flyflow.biz.form.FormStrategyFactory;
import cc.flyflow.biz.service.*;
import cc.flyflow.biz.utils.CoreHttpUtil;
import cc.flyflow.biz.vo.ProcessDataQueryVO;
import cc.flyflow.biz.vo.ProcessInstanceRecordVO;
import cc.flyflow.common.constants.ApproveResultEnum;
import cc.flyflow.common.constants.NodeUserTypeEnum;
import cc.flyflow.common.constants.ProcessInstanceConstant;
import cc.flyflow.common.dto.*;
import cc.flyflow.common.dto.flow.FormItemVO;
import cc.flyflow.common.dto.flow.NodeUser;
import cc.flyflow.common.dto.third.UserDto;
import cc.flyflow.common.service.biz.IRemoteService;
import cc.flyflow.common.utils.JsonUtil;
import cc.flyflow.common.utils.TenantUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/** 实例进程服务 */
@Service
@Slf4j
public class ProcessInstanceServiceImpl implements IProcessInstanceService {
    @Resource private IFileService fileService;
    @Resource private IProcessInstanceRecordService processInstanceRecordService;
    @Resource private IProcessInstanceCopyService processCopyService;

    @Resource private IProcessInstanceOperRecordService processInstanceOperRecordService;

    @Resource private IProcessNodeDataService processNodeDataService;

    @Resource private IProcessService processService;
    @Resource private IProcessInstanceNodeRecordService processNodeRecordService;
    @Resource private IProcessInstanceAssignUserRecordService processNodeRecordAssignUserService;
    @Resource private RedisTemplate redisTemplate;

    @Resource @Lazy private IRemoteService remoteService;

    /**
     * 启动流程
     *
     * @param processInstanceParamDto
     * @return
     */
    @Override
    public R startProcessInstance(ProcessInstanceParamDto processInstanceParamDto) {
        String uniqueId = processInstanceParamDto.getUniqueId();

        // 业务key
        Process process = processService.getByUniqueId(uniqueId);
        if (process == null) {
            return R.fail("流程不存在");
        }
        if (process.getHidden() || process.getStop()) {
            return R.fail("流程被禁用");
        }
        if (StrUtil.isNotBlank(processInstanceParamDto.getFlowId())) {
            if (!StrUtil.equals(process.getFlowId(), processInstanceParamDto.getFlowId())) {
                return R.fail("流程不存在");
            }
        }
        processInstanceParamDto.setFlowId(process.getFlowId());

        String userId = StpUtil.getLoginIdAsString();

        UserDto user = ApiStrategyFactory.getStrategy().getUser(userId);

        processInstanceParamDto.setStartUserId(String.valueOf(userId));
        Map<String, Object> paramMap = processInstanceParamDto.getParamMap();
        NodeUser rootUser =
                NodeUser.builder()
                        .id(userId)
                        .name(user.getName())
                        .type(NodeUserTypeEnum.USER.getKey())
                        .build();
        paramMap.put(
                ProcessInstanceConstant.VariableKey.STARTER_USER, CollUtil.newArrayList(rootUser));

        processInstanceParamDto.setBizKey(process.getUniqueId());

        // 判断字段格式
        String formItems = process.getFormItems();
        List<FormItemVO> formItemVOS = JsonUtil.parseArray(formItems, FormItemVO.class);
        R checkValueResult = FormStrategyFactory.checkValue(formItemVOS, paramMap);
        if (!checkValueResult.isOk()) {
            return R.fail(checkValueResult.getMsg());
        }

        R<String> r = CoreHttpUtil.startProcess(processInstanceParamDto);

        if (!r.isOk()) {
            return R.fail(r.getMsg());
        }
        String data = r.getData();

        processInstanceOperRecordService.saveStartProcessRecord(
                userId, data, processInstanceParamDto.getFlowId());

        return R.success(data);
    }

    /**
     * 查询已办任务的流程实例
     *
     * @param pageVO
     * @return
     */
    @Override
    public R queryMineDoneProcessInstance(ProcessDataQueryVO pageVO) {

        List<String> flowIdList = pageVO.getFlowIdList();
        if (CollUtil.isNotEmpty(flowIdList)) {
            flowIdList = processService.getAllRelatedFlowId(flowIdList).getData();
        }

        ProcessQueryParamDto processQueryParamDto =
                BeanUtil.copyProperties(pageVO, ProcessQueryParamDto.class);
        processQueryParamDto.setAssign(StpUtil.getLoginIdAsString());
        processQueryParamDto.setFlowIdList(flowIdList);
        R<PageResultDto<ProcessInstanceDto>> r =
                CoreHttpUtil.queryCompletedProcessInstance(processQueryParamDto);

        PageResultDto<ProcessInstanceDto> pageResultDto = r.getData();
        List<ProcessInstanceDto> records = pageResultDto.getRecords();
        if (CollUtil.isEmpty(records)) {
            return R.success(pageResultDto);
        }

        Set<String> processInstanceIdSet =
                records.stream().map(w -> w.getProcessInstanceId()).collect(Collectors.toSet());

        // 流程实例记录
        List<ProcessInstanceRecord> processInstanceRecordList =
                processInstanceRecordService
                        .lambdaQuery()
                        .in(ProcessInstanceRecord::getProcessInstanceId, processInstanceIdSet)
                        .list();

        // 发起人
        Set<String> startUserIdSet =
                processInstanceRecordList.stream()
                        .map(w -> w.getUserId())
                        .collect(Collectors.toSet());

        List<UserDto> startUserList = new ArrayList<>();
        {
            for (String userIds : startUserIdSet) {
                UserDto user = ApiStrategyFactory.getStrategy().getUser(userIds);
                startUserList.add(user);
            }
        }

        for (ProcessInstanceDto record : records) {

            ProcessInstanceRecord processInstanceRecord =
                    processInstanceRecordList.stream()
                            .filter(
                                    w ->
                                            StrUtil.equals(
                                                    w.getProcessInstanceId(),
                                                    record.getProcessInstanceId()))
                            .findAny()
                            .orElse(null);

            if (processInstanceRecord != null) {

                record.setProcessName(processInstanceRecord.getName());

                UserDto startUser =
                        startUserList.stream()
                                .filter(w -> w.getId().equals(processInstanceRecord.getUserId()))
                                .findAny()
                                .orElse(null);
                record.setGroupName(processInstanceRecord.getGroupName());
                record.setStartUserName(startUser.getName());
                record.setProcessInstanceResult(processInstanceRecord.getResult());
                record.setProcessInstanceStatus(processInstanceRecord.getStatus());
            }
        }

        return R.success(pageResultDto);
    }

    /**
     * 流程结束
     *
     * @param processInstanceParamDto
     * @return
     */
    @Override
    public R processEndEvent(ProcessInstanceParamDto processInstanceParamDto) {
        processInstanceRecordService
                .lambdaUpdate()
                .set(ProcessInstanceRecord::getEndTime, new Date())
                .set(
                        !processInstanceParamDto.getCancel(),
                        ProcessInstanceRecord::getStatus,
                        ProcessInstanceRecordStatusEnum.YJS.getCode())
                .set(
                        processInstanceParamDto.getCancel(),
                        ProcessInstanceRecord::getStatus,
                        ProcessInstanceRecordStatusEnum.YCX.getCode())
                .set(
                        processInstanceParamDto.getCancel(),
                        ProcessInstanceRecord::getResult,
                        ApproveResultEnum.CANCEL.getValue())
                .set(
                        !processInstanceParamDto.getCancel(),
                        ProcessInstanceRecord::getResult,
                        processInstanceParamDto.getResult())
                .eq(
                        ProcessInstanceRecord::getProcessInstanceId,
                        processInstanceParamDto.getProcessInstanceId())
                .eq(ProcessInstanceRecord::getTenantId, processInstanceParamDto.getTenantId())
                .eq(ProcessInstanceRecord::getStatus, ProcessInstanceRecordStatusEnum.JXZ.getCode())
                .update(new ProcessInstanceRecord());

        return R.success();
    }

    /**
     * 查询流程实例
     *
     * @param pageDto
     * @return
     */
    @Override
    public R queryList(ProcessDataQueryVO pageDto) {

        // 查询所有的流程id
        List<String> allFlowIdList = new ArrayList<>();
        if (CollUtil.isNotEmpty(pageDto.getFlowIdList())) {
            List<String> data =
                    processService.getAllRelatedFlowId(pageDto.getFlowIdList()).getData();
            allFlowIdList.addAll(data);
        }

        List<NodeUser> starterList = pageDto.getStarterList();
        List<String> startTime = pageDto.getStartTime();
        List<String> finishTime = pageDto.getFinishTime();
        Page<ProcessInstanceRecord> instanceRecordPage =
                processInstanceRecordService
                        .lambdaQuery()
                        .eq(ProcessInstanceRecord::getTenantId, TenantUtil.get())
                        .in(
                                CollUtil.isNotEmpty(allFlowIdList),
                                ProcessInstanceRecord::getFlowId,
                                allFlowIdList)
                        .eq(
                                pageDto.getStatus() != null,
                                ProcessInstanceRecord::getStatus,
                                pageDto.getStatus())
                        .ge(
                                CollUtil.isNotEmpty(startTime) && startTime.size() >= 2,
                                ProcessInstanceRecord::getCreateTime,
                                (CollUtil.isNotEmpty(startTime) && startTime.size() >= 2)
                                        ? (DateUtil.parseDate(startTime.get(0)))
                                        : null)
                        .le(
                                CollUtil.isNotEmpty(startTime) && startTime.size() >= 2,
                                ProcessInstanceRecord::getCreateTime,
                                (CollUtil.isNotEmpty(startTime) && startTime.size() >= 2)
                                        ? (DateUtil.endOfDay(DateUtil.parseDate(startTime.get(1))))
                                        : null)
                        .ge(
                                CollUtil.isNotEmpty(finishTime) && finishTime.size() >= 2,
                                ProcessInstanceRecord::getEndTime,
                                (CollUtil.isNotEmpty(finishTime) && finishTime.size() >= 2)
                                        ? (DateUtil.parseDate(finishTime.get(0)))
                                        : null)
                        .le(
                                CollUtil.isNotEmpty(finishTime) && finishTime.size() >= 2,
                                ProcessInstanceRecord::getEndTime,
                                (CollUtil.isNotEmpty(finishTime) && finishTime.size() >= 2)
                                        ? (DateUtil.endOfDay(DateUtil.parseDate(finishTime.get(1))))
                                        : null)
                        .in(
                                CollUtil.isNotEmpty(starterList),
                                ProcessInstanceRecord::getUserId,
                                starterList == null
                                        ? new ArrayList<>()
                                        : starterList.stream()
                                                .map(w -> w.getId())
                                                .collect(Collectors.toList()))
                        .eq(
                                StrUtil.isNotBlank(pageDto.getProcessBizCode()),
                                ProcessInstanceRecord::getProcessInstanceBizCode,
                                pageDto.getProcessBizCode())
                        .orderByDesc(ProcessInstanceRecord::getCreateTime)
                        .page(new Page<>(pageDto.getPageNum(), pageDto.getPageSize()));

        List<ProcessInstanceRecord> records = instanceRecordPage.getRecords();
        if (CollUtil.isEmpty(records)) {
            return R.success(instanceRecordPage);
        }

        // 流程配置
        Set<String> flowIdSet =
                records.stream().map(w -> w.getFlowId()).collect(Collectors.toSet());
        List<Process> processList =
                processService
                        .lambdaQuery()
                        .in(Process::getFlowId, flowIdSet)
                        .eq(Process::getTenantId, TenantUtil.get())
                        .list();

        List<ProcessInstanceRecordVO> processInstanceRecordVOList =
                BeanUtil.copyToList(records, ProcessInstanceRecordVO.class);

        for (ProcessInstanceRecordVO record : processInstanceRecordVOList) {

            UserDto userDto = ApiStrategyFactory.getStrategy().getUser(record.getUserId());

            record.setFormData(null);
            record.setProcess(null);
            record.setRootUserAvatarUrl(userDto.getAvatarUrl());
            record.setRootUserName(userDto.getName());
        }
        Page page = BeanUtil.copyProperties(instanceRecordPage, Page.class);
        page.setRecords(processInstanceRecordVOList);

        return R.success(page);
    }

    /**
     * 查询流程实例详情
     *
     * @param processInstanceId
     * @return
     */
    @Override
    public R queryDetailByProcessInstanceId(String processInstanceId) {

        ProcessInstanceRecord processInstanceRecord =
                processInstanceRecordService
                        .lambdaQuery()
                        .eq(ProcessInstanceRecord::getProcessInstanceId, processInstanceId)
                        .eq(ProcessInstanceRecord::getTenantId, TenantUtil.get())
                        .one();

        ProcessInstanceRecordVO record =
                BeanUtil.copyProperties(processInstanceRecord, ProcessInstanceRecordVO.class);

        // 流程配置

        UserDto userDto =
                ApiStrategyFactory.getStrategy().getUser(processInstanceRecord.getUserId());

        record.setFormData(null);
        record.setProcess(null);
        record.setRootUserAvatarUrl(userDto.getAvatarUrl());
        record.setRootUserName(userDto.getName());
        record.setCancelEnable(false);

        return R.success(record);
    }

    /**
     * 查询处理中的任务
     *
     * @param processInstanceId
     * @return
     */
    @Override
    public R queryTaskListInProgress(String processInstanceId) {

        R<List<TaskDto>> listR = CoreHttpUtil.queryTaskAssignee(null, processInstanceId);
        if (!listR.isOk()) {
            return listR;
        }
        List<TaskDto> taskDtoList = listR.getData();
        for (TaskDto taskDto : taskDtoList) {
            UserDto userDto = ApiStrategyFactory.getStrategy().getUser(taskDto.getAssign());
            taskDto.setUserName(userDto.getName());
        }

        return listR;
    }
}
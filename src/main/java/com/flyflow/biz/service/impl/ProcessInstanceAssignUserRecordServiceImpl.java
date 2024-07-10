package com.flyflow.biz.service.impl;

import com.flyflow.biz.constants.ProcessInstanceAssignUserRecordStatusEnum;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyflow.biz.entity.ProcessInstanceAssignUserRecord;
import com.flyflow.biz.mapper.ProcessInstanceAssignUserRecordMapper;
import com.flyflow.biz.service.IClearService;
import com.flyflow.biz.service.IProcessInstanceAssignUserRecordService;
import com.flyflow.common.dto.ProcessInstanceAssignUserRecordParamDto;
import com.flyflow.common.dto.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 流程节点记录-执行人 服务实现类
 *
 * @author xiaoge
 * @since 2023-05-10
 */
@Slf4j
@Service
public class ProcessInstanceAssignUserRecordServiceImpl
        extends ServiceImpl<ProcessInstanceAssignUserRecordMapper, ProcessInstanceAssignUserRecord>
        implements IProcessInstanceAssignUserRecordService, IClearService {

    /**
     * 设置执行人
     *
     * @param processInstanceAssignUserRecordParamDto
     * @return
     */
    @Override
    public R createTaskEvent(
            ProcessInstanceAssignUserRecordParamDto processInstanceAssignUserRecordParamDto) {
        {

            // 在委派或者转办时 会有一个任务 不同的执行人 所以要更新状态为从进行中为已结束
            this.lambdaUpdate()
                    .eq(
                            ProcessInstanceAssignUserRecord::getTaskId,
                            processInstanceAssignUserRecordParamDto.getTaskId())
                    .eq(
                            ProcessInstanceAssignUserRecord::getExecutionId,
                            processInstanceAssignUserRecordParamDto.getExecutionId())
                    .eq(
                            ProcessInstanceAssignUserRecord::getStatus,
                            ProcessInstanceAssignUserRecordStatusEnum.JXZ.getCode())
                    .set(
                            ProcessInstanceAssignUserRecord::getStatus,
                            ProcessInstanceAssignUserRecordStatusEnum.YJS.getCode())
                    .set(
                            ProcessInstanceAssignUserRecord::getTaskType,
                            processInstanceAssignUserRecordParamDto.getTaskType())
                    .set(ProcessInstanceAssignUserRecord::getEndTime, new Date())
                    .update(new ProcessInstanceAssignUserRecord());
        }

        // 新增一条数据
        ProcessInstanceAssignUserRecord processInstanceAssignUserRecord =
                BeanUtil.copyProperties(
                        processInstanceAssignUserRecordParamDto,
                        ProcessInstanceAssignUserRecord.class);
        processInstanceAssignUserRecord.setStartTime(new Date());
        processInstanceAssignUserRecord.setStatus(
                ProcessInstanceAssignUserRecordStatusEnum.JXZ.getCode());
        processInstanceAssignUserRecord.setTaskType("");
        this.save(processInstanceAssignUserRecord);

        return R.success();
    }

    /**
     * 任务完成通知
     *
     * @param processInstanceAssignUserRecordParamDto
     * @return
     */
    @Override
    public R taskCompletedEvent(
            ProcessInstanceAssignUserRecordParamDto processInstanceAssignUserRecordParamDto) {
        this.lambdaUpdate()
                .eq(
                        ProcessInstanceAssignUserRecord::getTaskId,
                        processInstanceAssignUserRecordParamDto.getTaskId())
                .eq(
                        ProcessInstanceAssignUserRecord::getUserId,
                        processInstanceAssignUserRecordParamDto.getUserId())
                .eq(
                        ProcessInstanceAssignUserRecord::getProcessInstanceId,
                        processInstanceAssignUserRecordParamDto.getProcessInstanceId())
                .eq(
                        ProcessInstanceAssignUserRecord::getStatus,
                        ProcessInstanceAssignUserRecordStatusEnum.JXZ.getCode())
                .set(
                        !processInstanceAssignUserRecordParamDto.getAuto(),
                        ProcessInstanceAssignUserRecord::getStatus,
                        ProcessInstanceAssignUserRecordStatusEnum.YJS.getCode())
                .set(
                        processInstanceAssignUserRecordParamDto.getAuto(),
                        ProcessInstanceAssignUserRecord::getStatus,
                        ProcessInstanceAssignUserRecordStatusEnum.WCL.getCode())
                .set(ProcessInstanceAssignUserRecord::getEndTime, new Date())
                .set(
                        ProcessInstanceAssignUserRecord::getData,
                        processInstanceAssignUserRecordParamDto.getData())
                .set(
                        ProcessInstanceAssignUserRecord::getLocalData,
                        processInstanceAssignUserRecordParamDto.getLocalData())
                .set(
                        ProcessInstanceAssignUserRecord::getTaskType,
                        processInstanceAssignUserRecordParamDto.getTaskType())
                .set(
                        ProcessInstanceAssignUserRecord::getAuto,
                        processInstanceAssignUserRecordParamDto.getAuto())
                .update(new ProcessInstanceAssignUserRecord());

        return R.success();
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
        ProcessInstanceAssignUserRecord processInstanceAssignUserRecord =
                this.lambdaQuery()
                        .eq(
                                ProcessInstanceAssignUserRecord::getTaskId,
                                processInstanceAssignUserRecordParamDto.getTaskId())
                        .eq(
                                ProcessInstanceAssignUserRecord::getUserId,
                                processInstanceAssignUserRecordParamDto.getUserId())
                        .eq(
                                ProcessInstanceAssignUserRecord::getProcessInstanceId,
                                processInstanceAssignUserRecordParamDto.getProcessInstanceId())
                        .eq(
                                ProcessInstanceAssignUserRecord::getStatus,
                                ProcessInstanceAssignUserRecordStatusEnum.JXZ.getCode())
                        .one();
        if (processInstanceAssignUserRecord != null) {
            processInstanceAssignUserRecord.setStatus(
                    ProcessInstanceAssignUserRecordStatusEnum.WCL.getCode());
            processInstanceAssignUserRecord.setEndTime(new Date());
            processInstanceAssignUserRecord.setData(
                    processInstanceAssignUserRecordParamDto.getData());
            processInstanceAssignUserRecord.setLocalData(
                    processInstanceAssignUserRecordParamDto.getLocalData());
            processInstanceAssignUserRecord.setTaskType(
                    processInstanceAssignUserRecordParamDto.getTaskType());
            this.updateById(processInstanceAssignUserRecord);
        }

        return R.success();
    }

    /**
     * 清理数据
     *
     * @param uniqueId 流程唯一id
     * @param flowIdList process表 流程id集合
     * @param processIdList process表的注解id集合
     * @param tenantId 租户id
     */
    @Override
    public void clearProcess(
            String uniqueId, List<String> flowIdList, List<Long> processIdList, String tenantId) {
        this.lambdaUpdate()
                .in(ProcessInstanceAssignUserRecord::getFlowId, flowIdList)
                .eq(ProcessInstanceAssignUserRecord::getTenantId, tenantId)
                .remove();
    }
}
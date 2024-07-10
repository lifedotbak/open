package com.flyflow.biz.service;

import com.flyflow.biz.entity.ProcessInstanceAssignUserRecord;
import com.flyflow.common.dto.ProcessInstanceAssignUserRecordParamDto;
import com.flyflow.common.dto.R;
import com.github.yulichang.base.MPJBaseService;

/**
 * 流程节点记录-执行人 服务类
 *
 * @author xiaoge
 * @since 2023-05-10
 */
public interface IProcessInstanceAssignUserRecordService
        extends MPJBaseService<ProcessInstanceAssignUserRecord> {
    /**
     * 设置执行人
     *
     * @param processInstanceAssignUserRecordParamDto
     * @return
     */
    R createTaskEvent(
            ProcessInstanceAssignUserRecordParamDto processInstanceAssignUserRecordParamDto);

    /**
     * 任务完成通知
     *
     * @param processInstanceAssignUserRecordParamDto
     * @return
     */
    R taskCompletedEvent(
            ProcessInstanceAssignUserRecordParamDto processInstanceAssignUserRecordParamDto);

    /**
     * 任务结束
     *
     * @param processInstanceAssignUserRecordParamDto
     * @return
     */
    R taskEndEvent(ProcessInstanceAssignUserRecordParamDto processInstanceAssignUserRecordParamDto);
}
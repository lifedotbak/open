package com.spyker.flowable.biz.service;

import com.spyker.flowable.common.dto.R;
import com.spyker.flowable.common.dto.TaskParamDto;

/** 任务处理 */
public interface ITaskService {

    /**
     * 完成任务
     *
     * @param taskParamDto
     * @return
     */
    R completeTask(TaskParamDto taskParamDto);

    /**
     * 获取任务信息
     *
     * @param taskId 任务id
     * @return
     */
    R getTask(String taskId);
}
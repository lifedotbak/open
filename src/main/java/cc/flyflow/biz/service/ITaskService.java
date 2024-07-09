package cc.flyflow.biz.service;

import cc.flyflow.common.dto.R;
import cc.flyflow.common.dto.TaskParamDto;

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
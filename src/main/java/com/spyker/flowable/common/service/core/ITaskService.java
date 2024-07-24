package com.spyker.flowable.common.service.core;

import com.spyker.flowable.common.dto.R;
import com.spyker.flowable.common.dto.TaskDto;
import com.spyker.flowable.common.dto.TaskParamDto;
import com.spyker.flowable.common.dto.VariableQueryParamDto;

import java.util.List;

/**
 * @author Huijun Zhao
 * @description
 * @date 2023-10-16 16:59
 */
public interface ITaskService {
    /**
     * 完成任务
     *
     * @param taskParamDto
     * @return
     */
    R complete(TaskParamDto taskParamDto);

    /**
     * 查询任务
     *
     * @param taskId
     * @param userId
     * @return
     */
    R queryTask(String taskId, String userId);

    /**
     * 查询任务评论
     *
     * @param paramDto
     * @return
     */
    R queryTaskComments(VariableQueryParamDto paramDto);

    /**
     * 查询任务的执行人
     *
     * @param taskParamDto
     * @return
     */
    R<List<TaskDto>> queryTaskAssignee(TaskParamDto taskParamDto);
}
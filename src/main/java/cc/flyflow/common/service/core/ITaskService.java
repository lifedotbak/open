package cc.flyflow.common.service.core;

import cc.flyflow.common.dto.R;
import cc.flyflow.common.dto.TaskDto;
import cc.flyflow.common.dto.TaskParamDto;
import cc.flyflow.common.dto.VariableQueryParamDto;

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
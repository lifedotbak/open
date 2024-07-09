package cc.flyflow.biz.service;

import cc.flyflow.biz.vo.ProcessDataQueryVO;
import cc.flyflow.common.dto.*;

/** 流程实例进程 */
public interface IProcessInstanceService {

    /**
     * 启动流程
     *
     * @param processInstanceParamDto
     * @return
     */
    R startProcessInstance(ProcessInstanceParamDto processInstanceParamDto);

    /**
     * 查询已办任务的流程实例
     *
     * @param pageVO
     * @return
     */
    R queryMineDoneProcessInstance(ProcessDataQueryVO pageVO);

    /**
     * 流程结束
     *
     * @param processInstanceParamDto
     * @return
     */
    R processEndEvent(ProcessInstanceParamDto processInstanceParamDto);

    /**
     * 查询流程实例
     *
     * @param pageDto
     * @return
     */
    R queryList(ProcessDataQueryVO pageDto);

    /**
     * 查询流程实例详情
     *
     * @param processInstanceId
     * @return
     */
    R queryDetailByProcessInstanceId(String processInstanceId);

    /**
     * 查询处理中的任务
     *
     * @param processInstanceId
     * @return
     */
    R queryTaskListInProgress(String processInstanceId);
}
package com.flyflow.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flyflow.biz.entity.ProcessInstanceRecord;

/**
 * 流程记录 服务类
 *
 * @author Vincent
 * @since 2023-05-07
 */
public interface IProcessInstanceRecordService extends IService<ProcessInstanceRecord> {

    /**
     * 根据流程实例id查询流程记录
     *
     * @param processInstanceId 流程实例id
     * @return 流程实例
     */
    ProcessInstanceRecord getByProcessInstanceId(String processInstanceId);
}
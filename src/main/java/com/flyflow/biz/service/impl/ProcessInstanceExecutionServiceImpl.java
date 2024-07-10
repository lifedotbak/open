package com.flyflow.biz.service.impl;

import com.flyflow.biz.entity.ProcessInstanceExecution;
import com.flyflow.biz.mapper.ProcessInstanceExecutionMapper;
import com.flyflow.biz.service.IClearService;
import com.flyflow.biz.service.IProcessInstanceExecutionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhj
 * @version 1.0
 * @description: TODO
 * @date 2024/4/2 14:45
 */
@Service
@Slf4j
public class ProcessInstanceExecutionServiceImpl
        extends ServiceImpl<ProcessInstanceExecutionMapper, ProcessInstanceExecution>
        implements IProcessInstanceExecutionService, IClearService {
    /**
     * 清理流程数据
     *
     * @param uniqueId 流程唯一id
     * @param flowIdList process表 流程id集合
     * @param processIdList process表的注解id集合
     * @param tenantId 租户id
     */
    @Override
    public void clearProcess(
            String uniqueId, List<String> flowIdList, List<Long> processIdList, String tenantId) {
        this.lambdaUpdate().in(ProcessInstanceExecution::getFlowId, flowIdList).remove();
    }
}
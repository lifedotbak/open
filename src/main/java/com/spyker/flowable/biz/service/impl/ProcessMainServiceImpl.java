package com.spyker.flowable.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.spyker.flowable.biz.entity.ProcessMain;
import com.spyker.flowable.biz.mapper.ProcessMainMapper;
import com.spyker.flowable.biz.service.IClearService;
import com.spyker.flowable.biz.service.IProcessMainService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProcessMainServiceImpl extends ServiceImpl<ProcessMainMapper, ProcessMain>
        implements IProcessMainService, IClearService {
    /**
     * 清理数据
     *
     * @param uniqueId 流程唯一id
     * @param flowIdList process表 流程id集合
     * @param processIdList process表的注解id集合
     * @param tenantId
     */
    @Override
    public void clearProcess(
            String uniqueId, List<String> flowIdList, List<Long> processIdList, String tenantId) {
        this.lambdaUpdate()
                .eq(ProcessMain::getUniqueId, uniqueId)
                .eq(ProcessMain::getTenantId, tenantId)
                .remove();
    }
}
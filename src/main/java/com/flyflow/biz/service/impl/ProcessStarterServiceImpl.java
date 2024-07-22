package com.flyflow.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyflow.biz.entity.ProcessStarter;
import com.flyflow.biz.mapper.ProcessStarterMapper;
import com.flyflow.biz.service.IClearService;
import com.flyflow.biz.service.IProcessStarterService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程发起人 服务实现类
 *
 * @author Vincent
 * @since 2023-05-30
 */
@Service
public class ProcessStarterServiceImpl extends ServiceImpl<ProcessStarterMapper, ProcessStarter>
        implements IProcessStarterService, IClearService {

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
                .in(ProcessStarter::getProcessId, processIdList)
                .eq(ProcessStarter::getTenantId, tenantId)
                .remove();
    }
}
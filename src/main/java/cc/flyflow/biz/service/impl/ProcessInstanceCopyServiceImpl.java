package cc.flyflow.biz.service.impl;

import cc.flyflow.biz.entity.ProcessInstanceCopy;
import cc.flyflow.biz.mapper.ProcessInstanceCopyMapper;
import cc.flyflow.biz.service.IClearService;
import cc.flyflow.biz.service.IProcessInstanceCopyService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程抄送数据 服务实现类
 *
 * @author Vincent
 * @since 2023-05-20
 */
@Service
public class ProcessInstanceCopyServiceImpl
        extends ServiceImpl<ProcessInstanceCopyMapper, ProcessInstanceCopy>
        implements IProcessInstanceCopyService, IClearService {

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
                .in(ProcessInstanceCopy::getFlowId, flowIdList)
                .eq(ProcessInstanceCopy::getTenantId, tenantId)
                .remove();
    }
}
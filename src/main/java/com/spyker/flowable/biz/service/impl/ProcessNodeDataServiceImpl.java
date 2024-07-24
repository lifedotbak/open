package com.spyker.flowable.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.spyker.flowable.biz.entity.ProcessNodeData;
import com.spyker.flowable.biz.mapper.ProcessNodeDataMapper;
import com.spyker.flowable.biz.service.IProcessNodeDataService;
import com.spyker.flowable.common.constants.ProcessInstanceConstant;
import com.spyker.flowable.common.dto.ProcessNodeDataDto;
import com.spyker.flowable.common.dto.R;
import com.spyker.flowable.common.dto.flow.Node;
import com.spyker.framework.util.JsonUtil;
import com.spyker.flowable.common.utils.TenantUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 流程节点数据 服务实现类
 *
 * @author Vincent
 * @since 2023-05-07
 */
@Slf4j
@Service
public class ProcessNodeDataServiceImpl extends ServiceImpl<ProcessNodeDataMapper, ProcessNodeData>
        implements IProcessNodeDataService {

    /**
     * 保存流程节点数据
     *
     * @param processNodeDataDto
     * @return
     */
    @Override
    public R saveNodeData(ProcessNodeDataDto processNodeDataDto) {

        ProcessNodeData processNodeData =
                BeanUtil.copyProperties(processNodeDataDto, ProcessNodeData.class);
        processNodeData.setTenantId(TenantUtil.get());
        this.save(processNodeData);

        return R.success();
    }

    /***
     * 获取节点数据
     * 5s内超过5次调用 升级为热点数据
     * @param flowId
     * @param nodeId
     * @return
     */
    @Cacheable(key = "#flowId+'_'+#nodeId", cacheNames = "stringCache")
    @Override
    public R<String> getNodeData(String flowId, String nodeId) {
        // 发起人用户任务
        if (StrUtil.startWith(nodeId, ProcessInstanceConstant.VariableKey.START_NODE)) {
            nodeId = ProcessInstanceConstant.VariableKey.START_NODE;
        }

        String tenantId = TenantUtil.get();
        ProcessNodeData processNodeData =
                this.lambdaQuery()
                        .eq(ProcessNodeData::getFlowId, flowId)
                        .eq(ProcessNodeData::getNodeId, nodeId)
                        .one();

        if (processNodeData == null) {
            return R.fail("数据不存在");
        }

        return R.success(processNodeData == null ? null : processNodeData.getData());
    }

    /**
     * 返回节点数据
     *
     * @param flowId
     * @param nodeId
     * @return
     */
    @Override
    public R<Node> getNode(String flowId, String nodeId) {
        String data = getNodeData(flowId, nodeId).getData();
        return R.success(JsonUtil.parseObject(data, Node.class));
    }
}
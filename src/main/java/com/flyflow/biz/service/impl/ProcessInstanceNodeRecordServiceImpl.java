package com.flyflow.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyflow.biz.constants.ProcessInstanceNodeRecordStatusEnum;
import com.flyflow.biz.entity.ProcessInstanceNodeRecord;
import com.flyflow.biz.entity.ProcessInstanceRecord;
import com.flyflow.biz.mapper.ProcessInstanceNodeRecordMapper;
import com.flyflow.biz.service.*;
import com.flyflow.common.constants.NodeTypeEnum;
import com.flyflow.common.dto.ProcessInstanceNodeRecordParamDto;
import com.flyflow.common.dto.R;
import com.flyflow.common.dto.flow.Node;
import com.flyflow.common.utils.JsonUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

/**
 * 流程节点记录 服务实现类
 *
 * @author xiaoge
 * @since 2023-05-10
 */
@Slf4j
@Service
public class ProcessInstanceNodeRecordServiceImpl
        extends ServiceImpl<ProcessInstanceNodeRecordMapper, ProcessInstanceNodeRecord>
        implements IProcessInstanceNodeRecordService, IClearService {

    @Resource private IProcessInstanceRecordService processInstanceRecordService;
    @Resource private IProcessNodeDataService processNodeDataService;
    @Resource private IProcessService processService;

    /**
     * 节点开始
     *
     * @param processInstanceNodeRecordParamDto
     * @return
     */
    @Override
    public R startNodeEvent(ProcessInstanceNodeRecordParamDto processInstanceNodeRecordParamDto) {
        String flowId = processInstanceNodeRecordParamDto.getFlowId();

        String nodeId = processInstanceNodeRecordParamDto.getNodeId();
        String parentNodeId = processInstanceNodeRecordParamDto.getParentNodeId();

        {
            // 判断是否存在同一个节点 执行中了
            Long count =
                    this.lambdaQuery()
                            .eq(
                                    ProcessInstanceNodeRecord::getProcessInstanceId,
                                    processInstanceNodeRecordParamDto.getProcessInstanceId())
                            .eq(
                                    ProcessInstanceNodeRecord::getNodeId,
                                    processInstanceNodeRecordParamDto.getNodeId())
                            .eq(
                                    StrUtil.isNotBlank(
                                            processInstanceNodeRecordParamDto.getTenantId()),
                                    ProcessInstanceNodeRecord::getTenantId,
                                    processInstanceNodeRecordParamDto.getTenantId())
                            .eq(
                                    ProcessInstanceNodeRecord::getExecutionId,
                                    processInstanceNodeRecordParamDto.getExecutionId())
                            .eq(
                                    ProcessInstanceNodeRecord::getStatus,
                                    ProcessInstanceNodeRecordStatusEnum.JXZ.getCode())
                            .count();
            if (count > 0) {
                log.info("有进行中的 不处理了 直接返回");
                return R.success();
            }
        }
        ProcessInstanceNodeRecord processNodeRecord =
                BeanUtil.copyProperties(
                        processInstanceNodeRecordParamDto, ProcessInstanceNodeRecord.class);
        processNodeRecord.setStartTime(new Date());
        processNodeRecord.setStatus(ProcessInstanceNodeRecordStatusEnum.JXZ.getCode());
        if (processInstanceNodeRecordParamDto.getNodeType() != null
                && processInstanceNodeRecordParamDto.getNodeType()
                        == NodeTypeEnum.END.getValue().intValue()) {
            processNodeRecord.setStatus(ProcessInstanceNodeRecordStatusEnum.YJS.getCode());
        }

        // 设置来源的上级id
        String lastNodeId =
                com.flyflow.biz.utils.NodeUtil.getLastNodeId(flowId, nodeId, parentNodeId);
        processNodeRecord.setParentNodeId(lastNodeId);

        this.save(processNodeRecord);

        // 当前流程

        ProcessInstanceRecord processInstanceRecord =
                processInstanceRecordService.getByProcessInstanceId(
                        processInstanceNodeRecordParamDto.getProcessInstanceId());

        Node currentProcessRootNode =
                JsonUtil.parseObject(processInstanceRecord.getProcess(), Node.class);

        log.info(
                "{}-{}上级跳转过来的id:{}",
                nodeId,
                processInstanceNodeRecordParamDto.getNodeName(),
                parentNodeId);

        // 设置executionId
        com.flyflow.biz.utils.NodeUtil.handleNodeAddExecutionId(
                currentProcessRootNode, nodeId, processInstanceNodeRecordParamDto.getExecutionId());
        processInstanceRecord.setProcess(JsonUtil.toJSONString(currentProcessRootNode));

        processInstanceRecordService.updateById(processInstanceRecord);

        return R.success();
    }

    /**
     * 节点结束
     *
     * @param processInstanceNodeRecordParamDto
     * @return
     */
    @Override
    public R endNodeEvent(ProcessInstanceNodeRecordParamDto processInstanceNodeRecordParamDto) {

        String processInstanceId = processInstanceNodeRecordParamDto.getProcessInstanceId();

        this.lambdaUpdate()
                .set(
                        ProcessInstanceNodeRecord::getStatus,
                        ProcessInstanceNodeRecordStatusEnum.YJS.getCode())
                .set(ProcessInstanceNodeRecord::getEndTime, new Date())
                .set(
                        ProcessInstanceNodeRecord::getData,
                        processInstanceNodeRecordParamDto.getData())
                .eq(ProcessInstanceNodeRecord::getProcessInstanceId, processInstanceId)
                .eq(
                        ProcessInstanceNodeRecord::getStatus,
                        ProcessInstanceNodeRecordStatusEnum.JXZ.getCode())
                .eq(
                        ProcessInstanceNodeRecord::getNodeId,
                        processInstanceNodeRecordParamDto.getNodeId())
                .eq(
                        ProcessInstanceNodeRecord::getTenantId,
                        processInstanceNodeRecordParamDto.getTenantId())
                .update(new ProcessInstanceNodeRecord());

        return R.success();
    }

    /**
     * 驳回
     *
     * @param processInstanceNodeRecordParamDto
     * @return
     */
    @Override
    public R cancelNodeEvent(ProcessInstanceNodeRecordParamDto processInstanceNodeRecordParamDto) {
        log.info(
                "节点取消：{}-{}",
                processInstanceNodeRecordParamDto.getNodeId(),
                processInstanceNodeRecordParamDto.getNodeName());
        String processInstanceId = processInstanceNodeRecordParamDto.getProcessInstanceId();
        String nodeId = processInstanceNodeRecordParamDto.getNodeId();
        this.lambdaUpdate()
                .set(
                        ProcessInstanceNodeRecord::getStatus,
                        ProcessInstanceNodeRecordStatusEnum.YCX.getCode())
                .eq(ProcessInstanceNodeRecord::getProcessInstanceId, processInstanceId)
                .eq(
                        ProcessInstanceNodeRecord::getTenantId,
                        processInstanceNodeRecordParamDto.getTenantId())
                .eq(ProcessInstanceNodeRecord::getNodeId, nodeId)
                .eq(
                        ProcessInstanceNodeRecord::getStatus,
                        ProcessInstanceNodeRecordStatusEnum.JXZ.getCode())
                .update(new ProcessInstanceNodeRecord());
        return R.success();
    }

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
                .in(ProcessInstanceNodeRecord::getFlowId, flowIdList)
                .eq(ProcessInstanceNodeRecord::getTenantId, tenantId)
                .remove();
    }
}
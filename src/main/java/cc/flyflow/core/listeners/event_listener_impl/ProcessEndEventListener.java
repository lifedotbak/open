package cc.flyflow.core.listeners.event_listener_impl;

import static cc.flyflow.common.constants.ProcessInstanceConstant.VariableKey.APPROVE_RESULT;
import static cc.flyflow.common.constants.ProcessInstanceConstant.VariableKey.REDIS_KEY_OF_FLOW_UNIQUE_ID;

import cc.flyflow.common.dto.ProcessInstanceParamDto;
import cc.flyflow.core.listeners.EventListenerStrategy;
import cc.flyflow.core.utils.BizHttpUtil;

import cn.hutool.core.util.StrUtil;

import lombok.extern.slf4j.Slf4j;

import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.event.impl.FlowableProcessTerminatedEventImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import javax.annotation.Resource;

/**
 * 流程结束了
 *
 * @author Huijun Zhao
 * @description
 * @date 2023-10-10 10:12
 */
@Slf4j
@Component
public class ProcessEndEventListener implements EventListenerStrategy, InitializingBean {

    @Resource private RedisTemplate redisTemplate;

    /**
     * 处理数据
     *
     * @param event
     * @return
     */
    @Override
    public void handle(FlowableEvent event) {

        // 流程开完成
        FlowableProcessTerminatedEventImpl e = (FlowableProcessTerminatedEventImpl) event;
        DelegateExecution execution = e.getExecution();
        String processInstanceId = e.getProcessInstanceId();
        ExecutionEntityImpl entity = (ExecutionEntityImpl) e.getEntity();
        Map<String, Object> variables = execution.getVariables();
        String flowId = entity.getProcessDefinitionKey();

        String tenantId = execution.getTenantId();

        // 结果
        Integer finalResult =
                execution.getVariable(
                        StrUtil.format("{}_{}", flowId, APPROVE_RESULT), Integer.class);

        ProcessInstanceParamDto processInstanceParamDto = new ProcessInstanceParamDto();
        processInstanceParamDto.setProcessInstanceId(processInstanceId);
        processInstanceParamDto.setCancel(false);
        processInstanceParamDto.setResult(finalResult);
        processInstanceParamDto.setFlowId(flowId);
        processInstanceParamDto.setParamMap(variables);
        processInstanceParamDto.setTenantId(tenantId);
        BizHttpUtil.processEndEvent(processInstanceParamDto);

        // 删除flowuniqueid
        redisTemplate.delete(StrUtil.format(REDIS_KEY_OF_FLOW_UNIQUE_ID, processInstanceId));
        // 删除执行人
        redisTemplate.delete(StrUtil.format("resolveAssignee_{}", processInstanceId));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(
                FlowableEngineEventType.PROCESS_COMPLETED_WITH_TERMINATE_END_EVENT.toString());
    }
}
package com.spyker.flowable.service;

import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.common.engine.impl.util.IoUtil;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/** 流程追踪图 */
@Component
public class ActivitiTracingChart {

    private static final Logger logger = LoggerFactory.getLogger(ActivitiTracingChart.class);

    @Autowired
    private HistoryService historyService;

    @Autowired private RepositoryService repositoryService;

    @Autowired private ProcessEngineConfiguration processEngineConfiguration;

    /**
     * 生成流程追踪图
     *
     * @param processInstanceId 流程实例id
     * @param outputStream 输出流
     */
    public void generateFlowChart(String processInstanceId, OutputStream outputStream) {
        if (StringUtils.isEmpty(processInstanceId)) {
            logger.error("processInstanceId is null");
            return;
        }
        // 获取历史流程实例
        HistoricProcessInstance historicProcessInstance =
                historyService
                        .createHistoricProcessInstanceQuery()
                        .processInstanceId(processInstanceId)
                        .singleResult();
        // 获取流程中已经执行的节点
        String processDefinitionId = historicProcessInstance.getProcessDefinitionId();
        List<String> highLightedActivities = new ArrayList<>();
        // 获得活动的节点
        List<HistoricActivityInstance> highLightedActivityList =
                historyService
                        .createHistoricActivityInstanceQuery()
                        .processInstanceId(processInstanceId)
                        .orderByHistoricActivityInstanceStartTime()
                        .asc()
                        .list();
        List<String> highLightedFlows = new ArrayList<>();
        for (HistoricActivityInstance tempActivity : highLightedActivityList) {
            String activityId = tempActivity.getActivityId();
            highLightedActivities.add(activityId);
            if ("sequenceFlow".equals(tempActivity.getActivityType())) {
                highLightedFlows.add(tempActivity.getActivityId());
            }
        }
        // 获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        ProcessEngineConfiguration engConf =
                processEngineConfiguration.getProcessEngineConfiguration();

        ProcessDiagramGenerator diagramGenerator = engConf.getProcessDiagramGenerator();
        InputStream in =
                diagramGenerator.generateDiagram(
                        bpmnModel,
                        "bmp",
                        highLightedActivities,
                        highLightedFlows,
                        "宋体",
                        "宋体",
                        "宋体",
                        engConf.getClassLoader(),
                        1.0,
                        true);
        byte[] buf = new byte[1024];
        int length;
        try {
            while ((length = in.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.closeSilently(outputStream);
            IoUtil.closeSilently(in);
        }
    }
}
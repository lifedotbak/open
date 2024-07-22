package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenProcessInstanceAssignUserRecordService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenProcessInstanceAssignUserRecord;
import com.spyker.commons.service.OpenProcessInstanceAssignUserRecordService;

import com.spyker.commons.search.OpenProcessInstanceAssignUserRecordSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 流程节点记录-执行人 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenProcessInstanceAssignUserRecordServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessInstanceAssignUserRecordService service;

    @Test
    public void get() {
        OpenProcessInstanceAssignUserRecord result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenProcessInstanceAssignUserRecord add = new OpenProcessInstanceAssignUserRecord();

        add.setFlowId("flowId");

        add.setProcessInstanceId("processInstanceId");

        add.setData("data");

        add.setNodeId("nodeId");

        add.setUserId("userId");

        add.setStatus(1);

        add.setExecutionId("executionId");

        add.setTaskId("taskId");

        add.setApproveDesc("approveDesc");

        add.setNodeName("nodeName");

        add.setTaskType("taskType");

        add.setLocalData("localData");

        add.setFlowUniqueId("flowUniqueId");

        add.setTenantId("tenantId");

        add.setParentExecutionId("parentExecutionId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenProcessInstanceAssignUserRecord update = new OpenProcessInstanceAssignUserRecord();

        update.setId("id");

        update.setFlowId("flowId");

        update.setProcessInstanceId("processInstanceId");

        update.setData("data");

        update.setNodeId("nodeId");

        update.setUserId("userId");
        update.setStatus(1);

        update.setExecutionId("executionId");

        update.setTaskId("taskId");

        update.setApproveDesc("approveDesc");

        update.setNodeName("nodeName");

        update.setTaskType("taskType");

        update.setLocalData("localData");

        update.setFlowUniqueId("flowUniqueId");

        update.setTenantId("tenantId");

        update.setParentExecutionId("parentExecutionId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenProcessInstanceAssignUserRecordSearch search =
                new OpenProcessInstanceAssignUserRecordSearch();

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setData("data");

        search.setNodeId("nodeId");

        search.setUserId("userId");
        search.setStatus(1);

        search.setExecutionId("executionId");

        search.setTaskId("taskId");

        search.setApproveDesc("approveDesc");

        search.setNodeName("nodeName");

        search.setTaskType("taskType");

        search.setLocalData("localData");

        search.setFlowUniqueId("flowUniqueId");

        search.setTenantId("tenantId");

        search.setParentExecutionId("parentExecutionId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenProcessInstanceAssignUserRecord> page = new Page<>(1, 10);

        OpenProcessInstanceAssignUserRecordSearch search =
                new OpenProcessInstanceAssignUserRecordSearch();

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setData("data");

        search.setNodeId("nodeId");

        search.setUserId("userId");

        search.setStatus(1);

        search.setExecutionId("executionId");

        search.setTaskId("taskId");

        search.setApproveDesc("approveDesc");

        search.setNodeName("nodeName");

        search.setTaskType("taskType");

        search.setLocalData("localData");

        search.setFlowUniqueId("flowUniqueId");

        search.setTenantId("tenantId");

        search.setParentExecutionId("parentExecutionId");

        service.queryPage(page, search);
    }
}
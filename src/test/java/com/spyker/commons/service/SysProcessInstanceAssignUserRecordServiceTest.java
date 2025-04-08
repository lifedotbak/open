package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysProcessInstanceAssignUserRecord;
import com.spyker.commons.search.SysProcessInstanceAssignUserRecordSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** 流程节点记录-执行人 服务测试类 */
@Slf4j
public class SysProcessInstanceAssignUserRecordServiceTest extends BaseTest {

    @Autowired private SysProcessInstanceAssignUserRecordService service;

    @Test
    public void add() {
        SysProcessInstanceAssignUserRecord add = new SysProcessInstanceAssignUserRecord();

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

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

        add.setAuto(1);

        add.setParentExecutionId("parentExecutionId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void get() {
        SysProcessInstanceAssignUserRecord result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void query() {
        SysProcessInstanceAssignUserRecordSearch search =
                new SysProcessInstanceAssignUserRecordSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

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
        search.setAuto(1);

        search.setParentExecutionId("parentExecutionId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysProcessInstanceAssignUserRecord> page = new Page<>(1, 10);

        SysProcessInstanceAssignUserRecordSearch search =
                new SysProcessInstanceAssignUserRecordSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

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

        search.setAuto(1);

        search.setParentExecutionId("parentExecutionId");

        service.queryPage(page, search);
    }

    @Test
    public void update() {
        SysProcessInstanceAssignUserRecord update = new SysProcessInstanceAssignUserRecord();

        update.setId("id");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

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
        update.setAuto(1);

        update.setParentExecutionId("parentExecutionId");

        log.info("update------>{}", update);

        service.update(update);
    }
}

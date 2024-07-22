package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenProcessInstanceNodeRecordService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenProcessInstanceNodeRecord;
import com.spyker.commons.service.OpenProcessInstanceNodeRecordService;

import com.spyker.commons.search.OpenProcessInstanceNodeRecordSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 流程节点记录 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenProcessInstanceNodeRecordServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessInstanceNodeRecordService service;

    @Test
    public void get() {
        OpenProcessInstanceNodeRecord result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenProcessInstanceNodeRecord add = new OpenProcessInstanceNodeRecord();

        add.setFlowId("flowId");

        add.setProcessInstanceId("processInstanceId");

        add.setData("data");

        add.setNodeId("nodeId");

        add.setNodeType("nodeType");

        add.setNodeName("nodeName");

        add.setStatus(1);

        add.setExecutionId("executionId");

        add.setParentNodeId("parentNodeId");

        add.setFlowUniqueId("flowUniqueId");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenProcessInstanceNodeRecord update = new OpenProcessInstanceNodeRecord();

        update.setId("id");

        update.setFlowId("flowId");

        update.setProcessInstanceId("processInstanceId");

        update.setData("data");

        update.setNodeId("nodeId");

        update.setNodeType("nodeType");

        update.setNodeName("nodeName");
        update.setStatus(1);

        update.setExecutionId("executionId");

        update.setParentNodeId("parentNodeId");

        update.setFlowUniqueId("flowUniqueId");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenProcessInstanceNodeRecordSearch search = new OpenProcessInstanceNodeRecordSearch();

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setData("data");

        search.setNodeId("nodeId");

        search.setNodeType("nodeType");

        search.setNodeName("nodeName");
        search.setStatus(1);

        search.setExecutionId("executionId");

        search.setParentNodeId("parentNodeId");

        search.setFlowUniqueId("flowUniqueId");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenProcessInstanceNodeRecord> page = new Page<>(1, 10);

        OpenProcessInstanceNodeRecordSearch search = new OpenProcessInstanceNodeRecordSearch();

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setData("data");

        search.setNodeId("nodeId");

        search.setNodeType("nodeType");

        search.setNodeName("nodeName");

        search.setStatus(1);

        search.setExecutionId("executionId");

        search.setParentNodeId("parentNodeId");

        search.setFlowUniqueId("flowUniqueId");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}
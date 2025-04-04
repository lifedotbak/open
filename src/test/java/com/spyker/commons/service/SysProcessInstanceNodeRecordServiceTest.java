package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysProcessInstanceNodeRecord;
import com.spyker.commons.search.SysProcessInstanceNodeRecordSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程节点记录 服务测试类
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessInstanceNodeRecordServiceTest extends BaseTest {

    @Autowired private SysProcessInstanceNodeRecordService service;

    @Test
    public void get() {
        SysProcessInstanceNodeRecord result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysProcessInstanceNodeRecord add = new SysProcessInstanceNodeRecord();

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

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
        SysProcessInstanceNodeRecord update = new SysProcessInstanceNodeRecord();

        update.setId("id");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

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
        SysProcessInstanceNodeRecordSearch search = new SysProcessInstanceNodeRecordSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

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
        IPage<SysProcessInstanceNodeRecord> page = new Page<>(1, 10);

        SysProcessInstanceNodeRecordSearch search = new SysProcessInstanceNodeRecordSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

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

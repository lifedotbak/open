package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenProcessInstanceExecutionService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenProcessInstanceExecution;
import com.spyker.commons.service.OpenProcessInstanceExecutionService;

import com.spyker.commons.search.OpenProcessInstanceExecutionSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 流程执行id数据 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenProcessInstanceExecutionServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessInstanceExecutionService service;

    @Test
    public void get() {
        OpenProcessInstanceExecution result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenProcessInstanceExecution add = new OpenProcessInstanceExecution();

        add.setTenantId("tenantId");

        add.setProcessInstanceId("processInstanceId");

        add.setNodeId("nodeId");

        add.setExecutionId("executionId");

        add.setParentExecutionId("parentExecutionId");

        add.setFlowId("flowId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenProcessInstanceExecution update = new OpenProcessInstanceExecution();

        update.setId("id");

        update.setTenantId("tenantId");

        update.setProcessInstanceId("processInstanceId");

        update.setNodeId("nodeId");

        update.setExecutionId("executionId");

        update.setParentExecutionId("parentExecutionId");

        update.setFlowId("flowId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenProcessInstanceExecutionSearch search = new OpenProcessInstanceExecutionSearch();

        search.setTenantId("tenantId");

        search.setProcessInstanceId("processInstanceId");

        search.setNodeId("nodeId");

        search.setExecutionId("executionId");

        search.setParentExecutionId("parentExecutionId");

        search.setFlowId("flowId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenProcessInstanceExecution> page = new Page<>(1, 10);

        OpenProcessInstanceExecutionSearch search = new OpenProcessInstanceExecutionSearch();

        search.setTenantId("tenantId");

        search.setProcessInstanceId("processInstanceId");

        search.setNodeId("nodeId");

        search.setExecutionId("executionId");

        search.setParentExecutionId("parentExecutionId");

        search.setFlowId("flowId");

        service.queryPage(page, search);
    }
}
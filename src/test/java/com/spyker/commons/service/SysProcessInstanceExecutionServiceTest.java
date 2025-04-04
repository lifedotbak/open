package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysProcessInstanceExecution;
import com.spyker.commons.search.SysProcessInstanceExecutionSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程执行id数据 服务测试类
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessInstanceExecutionServiceTest extends BaseTest {

    @Autowired private SysProcessInstanceExecutionService service;

    @Test
    public void get() {
        SysProcessInstanceExecution result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysProcessInstanceExecution add = new SysProcessInstanceExecution();

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

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
        SysProcessInstanceExecution update = new SysProcessInstanceExecution();

        update.setId("id");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

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
        SysProcessInstanceExecutionSearch search = new SysProcessInstanceExecutionSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

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
        IPage<SysProcessInstanceExecution> page = new Page<>(1, 10);

        SysProcessInstanceExecutionSearch search = new SysProcessInstanceExecutionSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setTenantId("tenantId");

        search.setProcessInstanceId("processInstanceId");

        search.setNodeId("nodeId");

        search.setExecutionId("executionId");

        search.setParentExecutionId("parentExecutionId");

        search.setFlowId("flowId");

        service.queryPage(page, search);
    }
}

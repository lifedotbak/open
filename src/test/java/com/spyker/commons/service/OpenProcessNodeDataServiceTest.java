package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenProcessNodeDataService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenProcessNodeData;
import com.spyker.commons.service.OpenProcessNodeDataService;

import com.spyker.commons.search.OpenProcessNodeDataSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 流程节点数据 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenProcessNodeDataServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessNodeDataService service;

    @Test
    public void get() {
        OpenProcessNodeData result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenProcessNodeData add = new OpenProcessNodeData();

        add.setFlowId("flowId");

        add.setData("data");

        add.setNodeId("nodeId");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenProcessNodeData update = new OpenProcessNodeData();

        update.setId("id");

        update.setFlowId("flowId");

        update.setData("data");

        update.setNodeId("nodeId");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenProcessNodeDataSearch search = new OpenProcessNodeDataSearch();

        search.setFlowId("flowId");

        search.setData("data");

        search.setNodeId("nodeId");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenProcessNodeData> page = new Page<>(1, 10);

        OpenProcessNodeDataSearch search = new OpenProcessNodeDataSearch();

        search.setFlowId("flowId");

        search.setData("data");

        search.setNodeId("nodeId");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}
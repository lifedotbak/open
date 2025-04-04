package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysProcessNodeData;
import com.spyker.commons.search.SysProcessNodeDataSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程节点数据 服务测试类
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessNodeDataServiceTest extends BaseTest {

    @Autowired private SysProcessNodeDataService service;

    @Test
    public void get() {
        SysProcessNodeData result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysProcessNodeData add = new SysProcessNodeData();

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setFlowId("flowId");

        add.setData("data");

        add.setNodeId("nodeId");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysProcessNodeData update = new SysProcessNodeData();

        update.setId("id");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setFlowId("flowId");

        update.setData("data");

        update.setNodeId("nodeId");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysProcessNodeDataSearch search = new SysProcessNodeDataSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setFlowId("flowId");

        search.setData("data");

        search.setNodeId("nodeId");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysProcessNodeData> page = new Page<>(1, 10);

        SysProcessNodeDataSearch search = new SysProcessNodeDataSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setFlowId("flowId");

        search.setData("data");

        search.setNodeId("nodeId");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}

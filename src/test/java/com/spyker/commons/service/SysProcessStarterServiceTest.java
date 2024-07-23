package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysProcessStarter;
import com.spyker.commons.search.SysProcessStarterSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程发起人范围 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessStarterServiceTest extends BaseTest {

    @Autowired private SysProcessStarterService service;

    @Test
    public void get() {
        SysProcessStarter result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysProcessStarter add = new SysProcessStarter();

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setTypeId("typeId");

        add.setContainChildrenDept(1);

        add.setTypeName("typeName");

        add.setProcessId("processId");

        add.setData("data");

        add.setTenantId("tenantId");

        add.setFlowId("flowId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysProcessStarter update = new SysProcessStarter();

        update.setId("id");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setTypeId("typeId");
        update.setContainChildrenDept(1);

        update.setTypeName("typeName");

        update.setProcessId("processId");

        update.setData("data");

        update.setTenantId("tenantId");

        update.setFlowId("flowId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysProcessStarterSearch search = new SysProcessStarterSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setTypeId("typeId");
        search.setContainChildrenDept(1);

        search.setTypeName("typeName");

        search.setProcessId("processId");

        search.setData("data");

        search.setTenantId("tenantId");

        search.setFlowId("flowId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysProcessStarter> page = new Page<>(1, 10);

        SysProcessStarterSearch search = new SysProcessStarterSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setTypeId("typeId");

        search.setContainChildrenDept(1);

        search.setTypeName("typeName");

        search.setProcessId("processId");

        search.setData("data");

        search.setTenantId("tenantId");

        search.setFlowId("flowId");

        service.queryPage(page, search);
    }
}
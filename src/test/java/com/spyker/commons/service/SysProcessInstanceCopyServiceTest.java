package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysProcessInstanceCopy;
import com.spyker.commons.search.SysProcessInstanceCopySearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 流程抄送数据 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessInstanceCopyServiceTest extends BaseTest {

    @Autowired private SysProcessInstanceCopyService service;

    @Test
    public void get() {
        SysProcessInstanceCopy result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysProcessInstanceCopy add = new SysProcessInstanceCopy();

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setStartUserId("startUserId");

        add.setFlowId("flowId");

        add.setProcessInstanceId("processInstanceId");

        add.setNodeId("nodeId");

        add.setGroupId("groupId");

        add.setGroupName("groupName");

        add.setProcessName("processName");

        add.setNodeName("nodeName");

        add.setFormData("formData");

        add.setUserId("userId");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysProcessInstanceCopy update = new SysProcessInstanceCopy();

        update.setId("id");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setStartUserId("startUserId");

        update.setFlowId("flowId");

        update.setProcessInstanceId("processInstanceId");

        update.setNodeId("nodeId");

        update.setGroupId("groupId");

        update.setGroupName("groupName");

        update.setProcessName("processName");

        update.setNodeName("nodeName");

        update.setFormData("formData");

        update.setUserId("userId");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysProcessInstanceCopySearch search = new SysProcessInstanceCopySearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setStartUserId("startUserId");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setNodeId("nodeId");

        search.setGroupId("groupId");

        search.setGroupName("groupName");

        search.setProcessName("processName");

        search.setNodeName("nodeName");

        search.setFormData("formData");

        search.setUserId("userId");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysProcessInstanceCopy> page = new Page<>(1, 10);

        SysProcessInstanceCopySearch search = new SysProcessInstanceCopySearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setStartUserId("startUserId");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setNodeId("nodeId");

        search.setGroupId("groupId");

        search.setGroupName("groupName");

        search.setProcessName("processName");

        search.setNodeName("nodeName");

        search.setFormData("formData");

        search.setUserId("userId");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}
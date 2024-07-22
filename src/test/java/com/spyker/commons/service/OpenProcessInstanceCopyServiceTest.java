package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenProcessInstanceCopyService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenProcessInstanceCopy;
import com.spyker.commons.service.OpenProcessInstanceCopyService;

import com.spyker.commons.search.OpenProcessInstanceCopySearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 流程抄送数据 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenProcessInstanceCopyServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessInstanceCopyService service;

    @Test
    public void get() {
        OpenProcessInstanceCopy result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenProcessInstanceCopy add = new OpenProcessInstanceCopy();

        add.setStartUserId("startUserId");

        add.setFlowId("flowId");

        add.setProcessInstanceId("processInstanceId");

        add.setNodeId("nodeId");

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
        OpenProcessInstanceCopy update = new OpenProcessInstanceCopy();

        update.setId("id");

        update.setStartUserId("startUserId");

        update.setFlowId("flowId");

        update.setProcessInstanceId("processInstanceId");

        update.setNodeId("nodeId");

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
        OpenProcessInstanceCopySearch search = new OpenProcessInstanceCopySearch();

        search.setStartUserId("startUserId");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setNodeId("nodeId");

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
        IPage<OpenProcessInstanceCopy> page = new Page<>(1, 10);

        OpenProcessInstanceCopySearch search = new OpenProcessInstanceCopySearch();

        search.setStartUserId("startUserId");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setNodeId("nodeId");

        search.setGroupName("groupName");

        search.setProcessName("processName");

        search.setNodeName("nodeName");

        search.setFormData("formData");

        search.setUserId("userId");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}
package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenProcessInstanceUserCopyService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenProcessInstanceUserCopy;
import com.spyker.commons.service.OpenProcessInstanceUserCopyService;

import com.spyker.commons.search.OpenProcessInstanceUserCopySearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 流程抄送数据--用户和实例唯一值 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenProcessInstanceUserCopyServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessInstanceUserCopyService service;

    @Test
    public void get() {
        OpenProcessInstanceUserCopy result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenProcessInstanceUserCopy add = new OpenProcessInstanceUserCopy();

        add.setStartUserId("startUserId");

        add.setFlowId("flowId");

        add.setProcessInstanceId("processInstanceId");

        add.setGroupName("groupName");

        add.setProcessName("processName");

        add.setUserId("userId");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenProcessInstanceUserCopy update = new OpenProcessInstanceUserCopy();

        update.setId("id");

        update.setStartUserId("startUserId");

        update.setFlowId("flowId");

        update.setProcessInstanceId("processInstanceId");

        update.setGroupName("groupName");

        update.setProcessName("processName");

        update.setUserId("userId");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenProcessInstanceUserCopySearch search = new OpenProcessInstanceUserCopySearch();

        search.setStartUserId("startUserId");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setGroupName("groupName");

        search.setProcessName("processName");

        search.setUserId("userId");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenProcessInstanceUserCopy> page = new Page<>(1, 10);

        OpenProcessInstanceUserCopySearch search = new OpenProcessInstanceUserCopySearch();

        search.setStartUserId("startUserId");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setGroupName("groupName");

        search.setProcessName("processName");

        search.setUserId("userId");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}
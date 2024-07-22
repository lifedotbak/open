package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenMessageService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenMessage;
import com.spyker.commons.service.OpenMessageService;

import com.spyker.commons.search.OpenMessageSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 通知消息 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenMessageServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenMessageService service;

    @Test
    public void get() {
        OpenMessage result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenMessage add = new OpenMessage();

        add.setMessageType("messageType");

        add.setUserId("userId");

        add.setBizUniqueId("bizUniqueId");

        add.setParam("param");

        add.setContent("content");

        add.setTitle("title");

        add.setFlowId("flowId");

        add.setProcessInstanceId("processInstanceId");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenMessage update = new OpenMessage();

        update.setId("id");

        update.setMessageType("messageType");

        update.setUserId("userId");

        update.setBizUniqueId("bizUniqueId");

        update.setParam("param");

        update.setContent("content");

        update.setTitle("title");

        update.setFlowId("flowId");

        update.setProcessInstanceId("processInstanceId");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenMessageSearch search = new OpenMessageSearch();

        search.setMessageType("messageType");

        search.setUserId("userId");

        search.setBizUniqueId("bizUniqueId");

        search.setParam("param");

        search.setContent("content");

        search.setTitle("title");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenMessage> page = new Page<>(1, 10);

        OpenMessageSearch search = new OpenMessageSearch();

        search.setMessageType("messageType");

        search.setUserId("userId");

        search.setBizUniqueId("bizUniqueId");

        search.setParam("param");

        search.setContent("content");

        search.setTitle("title");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}
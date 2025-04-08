package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysMessage;
import com.spyker.commons.search.SysMessageSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** 通知消息 服务测试类 */
@Slf4j
public class SysMessageServiceTest extends BaseTest {

    @Autowired private SysMessageService service;

    @Test
    public void add() {
        SysMessage add = new SysMessage();

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setMessageType("messageType");

        add.setReaded(1);

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
    public void delete() {
        service.delete("1");
    }

    @Test
    public void get() {
        SysMessage result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void query() {
        SysMessageSearch search = new SysMessageSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setMessageType("messageType");
        search.setReaded(1);

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
        IPage<SysMessage> page = new Page<>(1, 10);

        SysMessageSearch search = new SysMessageSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setMessageType("messageType");

        search.setReaded(1);

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

    @Test
    public void update() {
        SysMessage update = new SysMessage();

        update.setId("id");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setMessageType("messageType");
        update.setReaded(1);

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
}

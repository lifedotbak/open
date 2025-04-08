package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysProcessInstanceUserCopy;
import com.spyker.commons.search.SysProcessInstanceUserCopySearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** 流程抄送数据--用户和实例唯一值 服务测试类 */
@Slf4j
public class SysProcessInstanceUserCopyServiceTest extends BaseTest {

    @Autowired private SysProcessInstanceUserCopyService service;

    @Test
    public void add() {
        SysProcessInstanceUserCopy add = new SysProcessInstanceUserCopy();

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setStartUserId("startUserId");

        add.setFlowId("flowId");

        add.setProcessInstanceId("processInstanceId");

        add.setGroupId("groupId");

        add.setGroupName("groupName");

        add.setProcessName("processName");

        add.setUserId("userId");

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
        SysProcessInstanceUserCopy result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void query() {
        SysProcessInstanceUserCopySearch search = new SysProcessInstanceUserCopySearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setStartUserId("startUserId");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setGroupId("groupId");

        search.setGroupName("groupName");

        search.setProcessName("processName");

        search.setUserId("userId");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysProcessInstanceUserCopy> page = new Page<>(1, 10);

        SysProcessInstanceUserCopySearch search = new SysProcessInstanceUserCopySearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setStartUserId("startUserId");

        search.setFlowId("flowId");

        search.setProcessInstanceId("processInstanceId");

        search.setGroupId("groupId");

        search.setGroupName("groupName");

        search.setProcessName("processName");

        search.setUserId("userId");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }

    @Test
    public void update() {
        SysProcessInstanceUserCopy update = new SysProcessInstanceUserCopy();

        update.setId("id");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setStartUserId("startUserId");

        update.setFlowId("flowId");

        update.setProcessInstanceId("processInstanceId");

        update.setGroupId("groupId");

        update.setGroupName("groupName");

        update.setProcessName("processName");

        update.setUserId("userId");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }
}

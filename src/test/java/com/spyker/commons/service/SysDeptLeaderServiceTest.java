package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysDeptLeader;
import com.spyker.commons.search.SysDeptLeaderSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 部门-主管表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysDeptLeaderServiceTest extends BaseTest {

    @Autowired private SysDeptLeaderService service;

    @Test
    public void get() {
        SysDeptLeader result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysDeptLeader add = new SysDeptLeader();

        add.setDeptId("deptId");

        add.setDelFlag(1);

        add.setUserId("userId");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysDeptLeader update = new SysDeptLeader();

        update.setId("id");

        update.setDeptId("deptId");
        update.setDelFlag(1);

        update.setUserId("userId");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysDeptLeaderSearch search = new SysDeptLeaderSearch();

        search.setDeptId("deptId");
        search.setDelFlag(1);

        search.setUserId("userId");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysDeptLeader> page = new Page<>(1, 10);

        SysDeptLeaderSearch search = new SysDeptLeaderSearch();

        search.setDeptId("deptId");

        search.setDelFlag(1);

        search.setUserId("userId");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}
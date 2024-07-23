package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysUserRole;
import com.spyker.commons.search.SysUserRoleSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户-角色 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysUserRoleServiceTest extends BaseTest {

    @Autowired private SysUserRoleService service;

    @Test
    public void get() {
        SysUserRole result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysUserRole add = new SysUserRole();

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setUserId("userId");

        add.setRoleId("roleId");

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysUserRole update = new SysUserRole();

        update.setId("id");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setUserId("userId");

        update.setRoleId("roleId");

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysUserRoleSearch search = new SysUserRoleSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setUserId("userId");

        search.setRoleId("roleId");

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysUserRole> page = new Page<>(1, 10);

        SysUserRoleSearch search = new SysUserRoleSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setUserId("userId");

        search.setRoleId("roleId");

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}
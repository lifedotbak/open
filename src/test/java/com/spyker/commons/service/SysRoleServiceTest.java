package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysRole;
import com.spyker.commons.search.SysRoleSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色 服务测试类
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Slf4j
public class SysRoleServiceTest extends BaseTest {

    @Autowired private SysRoleService service;

    @Test
    public void get() {
        SysRole result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysRole add = new SysRole();

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setName("name");

        add.setUserId("userId");

        add.setRoleKey("roleKey");

        add.setStatus(1);

        add.setTenantId("tenantId");

        add.setRoleSort(1);

        add.setDataScope(1);

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysRole update = new SysRole();

        update.setId("id");
        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setName("name");

        update.setUserId("userId");

        update.setRoleKey("roleKey");
        update.setStatus(1);

        update.setTenantId("tenantId");
        update.setRoleSort(1);

        update.setDataScope(1);

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysRoleSearch search = new SysRoleSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setName("name");

        search.setUserId("userId");

        search.setRoleKey("roleKey");
        search.setStatus(1);

        search.setTenantId("tenantId");
        search.setRoleSort(1);

        search.setDataScope(1);

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysRole> page = new Page<>(1, 10);

        SysRoleSearch search = new SysRoleSearch();

        search.setDelFlag(1);

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setName("name");

        search.setUserId("userId");

        search.setRoleKey("roleKey");

        search.setStatus(1);

        search.setTenantId("tenantId");

        search.setRoleSort(1);

        search.setDataScope(1);

        service.queryPage(page, search);
    }
}

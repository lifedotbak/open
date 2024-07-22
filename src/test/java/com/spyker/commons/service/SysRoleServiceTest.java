package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.SysRole;
import com.spyker.commons.service.SysRoleService;

import com.spyker.commons.search.SysRoleSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 角色信息表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class SysRoleServiceTest extends BaseTest {

    // @formatter:off

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

        add.setRoleName("roleName");

        add.setRoleKey("roleKey");

        add.setRoleSort(1);

        add.setDataScope("dataScope");

        add.setStatus("status");

        add.setDelFlag("delFlag");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setRemark("remark");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysRole update = new SysRole();

        update.setRoleId("roleId");

        update.setRoleName("roleName");

        update.setRoleKey("roleKey");
        update.setRoleSort(1);

        update.setDataScope("dataScope");

        update.setStatus("status");

        update.setDelFlag("delFlag");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setRemark("remark");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysRoleSearch search = new SysRoleSearch();

        search.setRoleName("roleName");

        search.setRoleKey("roleKey");
        search.setRoleSort(1);

        search.setDataScope("dataScope");

        search.setStatus("status");

        search.setDelFlag("delFlag");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setRemark("remark");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysRole> page = new Page<>(1, 10);

        SysRoleSearch search = new SysRoleSearch();

        search.setRoleName("roleName");

        search.setRoleKey("roleKey");

        search.setRoleSort(1);

        search.setDataScope("dataScope");

        search.setStatus("status");

        search.setDelFlag("delFlag");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setRemark("remark");

        service.queryPage(page, search);
    }
}
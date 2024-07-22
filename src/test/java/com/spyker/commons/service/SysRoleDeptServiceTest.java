package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.SysRoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.SysRoleDept;
import com.spyker.commons.service.SysRoleDeptService;

import com.spyker.commons.search.SysRoleDeptSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 角色和部门关联表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class SysRoleDeptServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private SysRoleDeptService service;

    @Test
    public void get() {
        SysRoleDept result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysRoleDept add = new SysRoleDept();

        add.setRoleId("roleId");

        add.setDeptId("deptId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysRoleDept update = new SysRoleDept();

        update.setRoleId("roleId");

        update.setDeptId("deptId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysRoleDeptSearch search = new SysRoleDeptSearch();

        search.setRoleId("roleId");

        search.setDeptId("deptId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysRoleDept> page = new Page<>(1, 10);

        SysRoleDeptSearch search = new SysRoleDeptSearch();

        search.setRoleId("roleId");

        search.setDeptId("deptId");

        service.queryPage(page, search);
    }
}
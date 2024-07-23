package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysRoleDept;
import com.spyker.commons.search.SysRoleDeptSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色和部门关联表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysRoleDeptServiceTest extends BaseTest {

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

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysRoleDept update = new SysRoleDept();

        update.setId("id");

        update.setRoleId("roleId");

        update.setDeptId("deptId");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysRoleDeptSearch search = new SysRoleDeptSearch();

        search.setRoleId("roleId");

        search.setDeptId("deptId");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysRoleDept> page = new Page<>(1, 10);

        SysRoleDeptSearch search = new SysRoleDeptSearch();

        search.setRoleId("roleId");

        search.setDeptId("deptId");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        service.queryPage(page, search);
    }
}
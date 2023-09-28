package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.application.BaseTest;
import com.spyker.application.entity.SysRoleDept;
import com.spyker.application.search.SysRoleDeptSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 角色和部门关联表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@Slf4j
public class SysRoleDeptServiceTest extends BaseTest {

    @Autowired
    private SysRoleDeptService service;

    @Test
    public void get() {

        SysRoleDept result = service.getById("1");
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

        service.insert(add);
    }

    @Test
    public void update() {
        SysRoleDept update = new SysRoleDept();

        update.setRoleId("roleId");

        update.setDeptId("deptId");

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
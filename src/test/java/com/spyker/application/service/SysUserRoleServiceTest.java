package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.application.BaseTest;
import com.spyker.application.entity.SysUserRole;
import com.spyker.application.search.SysUserRoleSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户和角色关联表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@Slf4j
public class SysUserRoleServiceTest extends BaseTest {

    @Autowired
    private SysUserRoleService service;

    @Test
    public void get() {

        SysUserRole result = service.getById("1");
    }

    @Test
    public void delete() {

        service.delete("1");
    }

    @Test
    public void add() {
        SysUserRole add = new SysUserRole();

        service.insert(add);
    }

    @Test
    public void update() {
        SysUserRole update = new SysUserRole();

        update.setUserId("userId");

        update.setRoleId("roleId");

        service.update(update);
    }

    @Test
    public void query() {
        SysUserRoleSearch search = new SysUserRoleSearch();

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysUserRole> page = new Page<>(1, 10);

        SysUserRoleSearch search = new SysUserRoleSearch();

        service.queryPage(page, search);
    }

}
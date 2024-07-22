package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.SysUserRole;
import com.spyker.commons.service.SysUserRoleService;

import com.spyker.commons.search.SysUserRoleSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 用户和角色关联表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class SysUserRoleServiceTest extends BaseTest {

    // @formatter:off

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

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysUserRole update = new SysUserRole();

        update.setUserId("userId");

        update.setRoleId("roleId");

        log.info("update------>{}", update);

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
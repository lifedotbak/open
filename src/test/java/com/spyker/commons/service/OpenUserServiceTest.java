package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.OpenUserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.OpenUser;
import com.spyker.commons.service.OpenUserService;

import com.spyker.commons.search.OpenUserSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 用户表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenUserServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenUserService service;

    @Test
    public void get() {
        OpenUser result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        OpenUser add = new OpenUser();

        add.setName("name");

        add.setPinyin("pinyin");

        add.setPy("py");

        add.setAvatarUrl("avatarUrl");

        add.setPassword("password");

        add.setPhone("phone");

        add.setStatus(1);

        add.setTenantId("tenantId");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        OpenUser update = new OpenUser();

        update.setId("id");

        update.setName("name");

        update.setPinyin("pinyin");

        update.setPy("py");

        update.setAvatarUrl("avatarUrl");

        update.setPassword("password");

        update.setPhone("phone");
        update.setStatus(1);

        update.setTenantId("tenantId");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        OpenUserSearch search = new OpenUserSearch();

        search.setName("name");

        search.setPinyin("pinyin");

        search.setPy("py");

        search.setAvatarUrl("avatarUrl");

        search.setPassword("password");

        search.setPhone("phone");
        search.setStatus(1);

        search.setTenantId("tenantId");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<OpenUser> page = new Page<>(1, 10);

        OpenUserSearch search = new OpenUserSearch();

        search.setName("name");

        search.setPinyin("pinyin");

        search.setPy("py");

        search.setAvatarUrl("avatarUrl");

        search.setPassword("password");

        search.setPhone("phone");

        search.setStatus(1);

        search.setTenantId("tenantId");

        service.queryPage(page, search);
    }
}
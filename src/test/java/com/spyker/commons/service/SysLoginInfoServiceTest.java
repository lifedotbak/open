package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysLoginInfo;
import com.spyker.commons.search.SysLoginInfoSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 系统访问记录 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysLoginInfoServiceTest extends BaseTest {

    @Autowired private SysLoginInfoService service;

    @Test
    public void get() {
        SysLoginInfo result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysLoginInfo add = new SysLoginInfo();

        add.setUserName("userName");

        add.setIpAddress("ipaddr");

        add.setLoginLocation("loginLocation");

        add.setBrowser("browser");

        add.setOs("os");

        add.setStatus("status");

        add.setMsg("msg");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysLoginInfo update = new SysLoginInfo();

        update.setId("id");

        update.setUserName("userName");

        update.setIpAddress("ipaddr");

        update.setLoginLocation("loginLocation");

        update.setBrowser("browser");

        update.setOs("os");

        update.setStatus("status");

        update.setMsg("msg");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysLoginInfoSearch search = new SysLoginInfoSearch();

        search.setUserName("userName");

        search.setLoginLocation("loginLocation");

        search.setBrowser("browser");

        search.setOs("os");

        search.setStatus("status");

        search.setMsg("msg");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysLoginInfo> page = new Page<>(1, 10);

        SysLoginInfoSearch search = new SysLoginInfoSearch();

        search.setUserName("userName");

        search.setLoginLocation("loginLocation");

        search.setBrowser("browser");

        search.setOs("os");

        search.setStatus("status");

        search.setMsg("msg");

        service.queryPage(page, search);
    }
}
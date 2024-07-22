package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.SysLogininforService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.SysLogininfor;
import com.spyker.commons.service.SysLogininforService;

import com.spyker.commons.search.SysLogininforSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 系统访问记录 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class SysLogininforServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private SysLogininforService service;

    @Test
    public void get() {
        SysLogininfor result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysLogininfor add = new SysLogininfor();

        add.setUserName("userName");

        add.setIpaddr("ipaddr");

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
        SysLogininfor update = new SysLogininfor();

        update.setInfoId("infoId");

        update.setUserName("userName");

        update.setIpaddr("ipaddr");

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
        SysLogininforSearch search = new SysLogininforSearch();

        search.setUserName("userName");

        search.setIpaddr("ipaddr");

        search.setLoginLocation("loginLocation");

        search.setBrowser("browser");

        search.setOs("os");

        search.setStatus("status");

        search.setMsg("msg");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysLogininfor> page = new Page<>(1, 10);

        SysLogininforSearch search = new SysLogininforSearch();

        search.setUserName("userName");

        search.setIpaddr("ipaddr");

        search.setLoginLocation("loginLocation");

        search.setBrowser("browser");

        search.setOs("os");

        search.setStatus("status");

        search.setMsg("msg");

        service.queryPage(page, search);
    }
}
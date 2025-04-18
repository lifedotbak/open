package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysOperLog;
import com.spyker.commons.search.SysOperLogSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** 操作日志记录 服务测试类 */
@Slf4j
public class SysOperLogServiceTest extends BaseTest {

    @Autowired private SysOperLogService service;

    @Test
    public void add() {
        SysOperLog add = new SysOperLog();

        add.setTitle("title");

        add.setBusinessType(1);

        add.setMethod("method");

        add.setRequestMethod("requestMethod");

        add.setOperatorType(1);

        add.setDeptName("deptName");

        add.setOperUrl("operUrl");

        add.setOperIp("operIp");

        add.setOperParam("operParam");

        add.setJsonResult("jsonResult");

        add.setStatus(1);

        add.setErrorMsg("errorMsg");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void get() {
        SysOperLog result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void query() {
        SysOperLogSearch search = new SysOperLogSearch();

        search.setTitle("title");
        search.setBusinessType(1);

        search.setMethod("method");

        search.setRequestMethod("requestMethod");
        search.setOperatorType(1);

        search.setOperName("operName");

        search.setDeptName("deptName");

        search.setOperUrl("operUrl");

        search.setOperIp("operIp");

        search.setOperLocation("operLocation");

        search.setOperParam("operParam");

        search.setJsonResult("jsonResult");
        search.setStatus(1);

        search.setErrorMsg("errorMsg");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysOperLog> page = new Page<>(1, 10);

        SysOperLogSearch search = new SysOperLogSearch();

        search.setTitle("title");

        search.setBusinessType(1);

        search.setMethod("method");

        search.setRequestMethod("requestMethod");

        search.setOperatorType(1);

        search.setOperName("operName");

        search.setDeptName("deptName");

        search.setOperUrl("operUrl");

        search.setOperIp("operIp");

        search.setOperLocation("operLocation");

        search.setOperParam("operParam");

        search.setJsonResult("jsonResult");

        search.setStatus(1);

        search.setErrorMsg("errorMsg");

        service.queryPage(page, search);
    }

    @Test
    public void update() {
        SysOperLog update = new SysOperLog();

        update.setId("id");

        update.setTitle("title");
        update.setBusinessType(1);

        update.setMethod("method");

        update.setRequestMethod("requestMethod");
        update.setOperatorType(1);

        update.setDeptName("deptName");

        update.setOperUrl("operUrl");

        update.setOperIp("operIp");

        update.setOperParam("operParam");

        update.setJsonResult("jsonResult");
        update.setStatus(1);

        update.setErrorMsg("errorMsg");

        log.info("update------>{}", update);

        service.update(update);
    }
}

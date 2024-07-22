package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.SysJob;
import com.spyker.commons.service.SysJobService;

import com.spyker.commons.search.SysJobSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 定时任务调度表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class SysJobServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private SysJobService service;

    @Test
    public void get() {
        SysJob result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysJob add = new SysJob();

        add.setInvokeTarget("invokeTarget");

        add.setCronExpression("cronExpression");

        add.setMisfirePolicy("misfirePolicy");

        add.setConcurrent("concurrent");

        add.setStatus("status");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setRemark("remark");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysJob update = new SysJob();

        update.setJobId("jobId");

        update.setJobName("jobName");

        update.setJobGroup("jobGroup");

        update.setInvokeTarget("invokeTarget");

        update.setCronExpression("cronExpression");

        update.setMisfirePolicy("misfirePolicy");

        update.setConcurrent("concurrent");

        update.setStatus("status");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setRemark("remark");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysJobSearch search = new SysJobSearch();

        search.setInvokeTarget("invokeTarget");

        search.setCronExpression("cronExpression");

        search.setMisfirePolicy("misfirePolicy");

        search.setConcurrent("concurrent");

        search.setStatus("status");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setRemark("remark");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysJob> page = new Page<>(1, 10);

        SysJobSearch search = new SysJobSearch();

        search.setInvokeTarget("invokeTarget");

        search.setCronExpression("cronExpression");

        search.setMisfirePolicy("misfirePolicy");

        search.setConcurrent("concurrent");

        search.setStatus("status");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setRemark("remark");

        service.queryPage(page, search);
    }
}
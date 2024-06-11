package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysOssConfig;
import com.spyker.commons.search.SysOssConfigSearch;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 对象存储配置表 服务类
 *
 * @author CodeGenerator
 * @since 2023-12-21
 */
@Slf4j
public class SysOssConfigServiceTest extends BaseTest {

    @Autowired private SysOssConfigService service;

    @Test
    public void get() {

        SysOssConfig result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {

        service.delete("1");
    }

    @Test
    public void add() {
        SysOssConfig add = new SysOssConfig();

        add.setConfigKey("configKey");

        add.setAccessKey("accessKey");

        add.setSecretKey("secretKey");

        add.setBucketName("bucketName");

        add.setPrefix("prefix");

        add.setEndpoint("endpoint");

        add.setDomain("domain");

        add.setIsHttps("isHttps");

        add.setRegion("region");

        add.setAccessPolicy("accessPolicy");

        add.setStatus("status");

        add.setExt1("ext1");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setRemark("remark");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysOssConfig update = new SysOssConfig();

        update.setOssConfigId("ossConfigId");

        update.setConfigKey("configKey");

        update.setAccessKey("accessKey");

        update.setSecretKey("secretKey");

        update.setBucketName("bucketName");

        update.setPrefix("prefix");

        update.setEndpoint("endpoint");

        update.setDomain("domain");

        update.setIsHttps("isHttps");

        update.setRegion("region");

        update.setAccessPolicy("accessPolicy");

        update.setStatus("status");

        update.setExt1("ext1");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setRemark("remark");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysOssConfigSearch search = new SysOssConfigSearch();

        search.setConfigKey("configKey");

        search.setAccessKey("accessKey");

        search.setSecretKey("secretKey");

        search.setBucketName("bucketName");

        search.setPrefix("prefix");

        search.setEndpoint("endpoint");

        search.setDomain("domain");

        search.setIsHttps("isHttps");

        search.setRegion("region");

        search.setAccessPolicy("accessPolicy");

        search.setRemark("remark");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysOssConfig> page = new Page<>(1, 10);

        SysOssConfigSearch search = new SysOssConfigSearch();

        search.setConfigKey("configKey");

        search.setAccessKey("accessKey");

        search.setSecretKey("secretKey");

        search.setBucketName("bucketName");

        search.setPrefix("prefix");

        search.setEndpoint("endpoint");

        search.setDomain("domain");

        search.setIsHttps("isHttps");

        search.setRegion("region");

        search.setAccessPolicy("accessPolicy");

        search.setRemark("remark");

        service.queryPage(page, search);
    }
}
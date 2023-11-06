package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysConfig;
import com.spyker.commons.search.SysConfigSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 参数配置表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@Slf4j
public class SysConfigServiceTest extends BaseTest {

    @Autowired
    private SysConfigService service;

    @Test
    public void get() {

        SysConfig result = service.getById("1");
    }

    @Test
    public void delete() {

        service.delete("1");
    }

    @Test
    public void add() {
        SysConfig add = new SysConfig();

        add.setConfigName("configName");

        service.insert(add);
    }

    @Test
    public void update() {
        SysConfig update = new SysConfig();

        update.setConfigId("configId");

        update.setConfigName("configName");

        update.setConfigKey("configKey");

        update.setConfigValue("configValue");

        update.setConfigType("configType");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setRemark("remark");

        service.update(update);
    }

    @Test
    public void query() {
        SysConfigSearch search = new SysConfigSearch();

        search.setConfigName("configName");
        search.setConfigKey("configKey");
        search.setConfigType("configType");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysConfig> page = new Page<>(1, 10);

        SysConfigSearch search = new SysConfigSearch();

        search.setConfigName("configName");

        search.setConfigKey("configKey");

        search.setConfigType("configType");

        service.queryPage(page, search);
    }

}
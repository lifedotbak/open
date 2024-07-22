package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.SysDictData;
import com.spyker.commons.service.SysDictDataService;

import com.spyker.commons.search.SysDictDataSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 字典数据表 服务测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class SysDictDataServiceTest extends BaseTest {

    // @formatter:off

    @Autowired private SysDictDataService service;

    @Test
    public void get() {
        SysDictData result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete() {
        service.delete("1");
    }

    @Test
    public void add() {
        SysDictData add = new SysDictData();

        add.setDictSort(1);

        add.setDictLabel("dictLabel");

        add.setDictValue("dictValue");

        add.setDictType("dictType");

        add.setCssClass("cssClass");

        add.setListClass("listClass");

        add.setIsDefault("isDefault");

        add.setStatus("status");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setRemark("remark");

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update() {
        SysDictData update = new SysDictData();

        update.setDictCode("dictCode");
        update.setDictSort(1);

        update.setDictLabel("dictLabel");

        update.setDictValue("dictValue");

        update.setDictType("dictType");

        update.setCssClass("cssClass");

        update.setListClass("listClass");

        update.setIsDefault("isDefault");

        update.setStatus("status");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setRemark("remark");

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query() {
        SysDictDataSearch search = new SysDictDataSearch();

        search.setDictSort(1);

        search.setDictLabel("dictLabel");

        search.setDictValue("dictValue");

        search.setDictType("dictType");

        search.setCssClass("cssClass");

        search.setListClass("listClass");

        search.setIsDefault("isDefault");

        search.setStatus("status");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setRemark("remark");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysDictData> page = new Page<>(1, 10);

        SysDictDataSearch search = new SysDictDataSearch();

        search.setDictSort(1);

        search.setDictLabel("dictLabel");

        search.setDictValue("dictValue");

        search.setDictType("dictType");

        search.setCssClass("cssClass");

        search.setListClass("listClass");

        search.setIsDefault("isDefault");

        search.setStatus("status");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setRemark("remark");

        service.queryPage(page, search);
    }
}
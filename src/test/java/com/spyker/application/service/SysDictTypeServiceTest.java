package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.application.BaseTest;
import com.spyker.application.entity.SysDictType;
import com.spyker.application.search.SysDictTypeSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */

@Slf4j
public class SysDictTypeServiceTest extends BaseTest {

    @Autowired
    private SysDictTypeService service;

    @Test
    public void get() {

        SysDictType result = service.getById("1");
    }

    @Test
    public void delete() {

        service.delete("1");
    }

    @Test
    public void add() {
        SysDictType add = new SysDictType();

        add.setDictName("dictName");

        add.setDictType("dictType");

        add.setStatus("status");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setRemark("remark");

        service.insert(add);
    }

    @Test
    public void update() {
        SysDictType update = new SysDictType();

        update.setDictId("dictId");

        update.setDictName("dictName");

        update.setDictType("dictType");

        update.setStatus("status");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setRemark("remark");

        service.update(update);
    }

    @Test
    public void query() {
        SysDictTypeSearch search = new SysDictTypeSearch();

        search.setDictName("dictName");
        search.setDictType("dictType");
        search.setStatus("status");
        search.setCreateBy("createBy");
        search.setUpdateBy("updateBy");
        search.setRemark("remark");

        service.query(search);
    }

    @Test
    public void queryPage() {
        IPage<SysDictType> page = new Page<>(1, 10);

        SysDictTypeSearch search = new SysDictTypeSearch();

        search.setDictName("dictName");

        search.setDictType("dictType");

        search.setStatus("status");

        search.setCreateBy("createBy");

        search.setUpdateBy("updateBy");

        search.setRemark("remark");

        service.queryPage(page, search);
    }

}
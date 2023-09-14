package com.spyker.application.service;

import com.spyker.application.BaseTest;
import com.spyker.application.service.TestCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.application.entity.TestCode;
import com.spyker.application.service.TestCodeService;

import com.spyker.application.search.TestCodeSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-14
 */

@Slf4j
public class TestCodeServiceTest extends BaseTest {

    @Autowired
    private TestCodeService service;

    @Test
    public void get(){

        TestCode result = service.getById("1");
    }

    @Test
    public void delete(){

        service.delete("1");
    }

    @Test
    public void add(){
        TestCode add = new TestCode();

            add.setName("name");

        service.insert(add);
    }

    @Test
    public void update(){
        TestCode update = new TestCode();

              update.setId("id");
              update.setName("name");

        service.update(update);
    }

    @Test
    public void list(){
        TestCodeSearch search = new TestCodeSearch();

          search.setName("name");

        service.query(search);
    }

    @Test
    public void queryPage(){
        IPage<TestCode> page = new Page<>(1, 10);

        TestCodeSearch search = new TestCodeSearch();

              search.setName("name");

        service.queryPage(page, search);
    }

}
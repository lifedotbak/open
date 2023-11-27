package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.RInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.RInfo;
import com.spyker.commons.service.RInfoService;

import com.spyker.commons.search.RInfoSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-11-24
 */

@Slf4j
public class RInfoServiceTest extends BaseTest {

    @Autowired
    private RInfoService service;

    @Test
    public void get(){

        RInfo result = service.getById("1");
    }

    @Test
    public void delete(){

        service.delete("1");
    }

    @Test
    public void add(){
        RInfo add = new RInfo();



                    add.setName("name");









                    add.setInfo("info");


        service.insert(add);
    }

    @Test
    public void update(){
        RInfo update = new RInfo();


                    update.setId("id");

                    update.setName("name");



                    update.setInfo("info");

        service.update(update);
    }

    @Test
    public void query(){
        RInfoSearch search = new RInfoSearch();


                    search.setName("name");



                    search.setInfo("info");

        service.query(search);
    }

    @Test
    public void queryPage(){
        IPage<RInfo> page = new Page<>(1, 10);

        RInfoSearch search = new RInfoSearch();



                    search.setName("name");









                    search.setInfo("info");


        service.queryPage(page, search);
    }

}
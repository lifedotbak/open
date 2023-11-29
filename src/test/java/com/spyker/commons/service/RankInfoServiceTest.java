package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.RankInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.spyker.commons.entity.RankInfo;
import com.spyker.commons.service.RankInfoService;

import com.spyker.commons.search.RankInfoSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * Rank表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-11-28
 */

@Slf4j
public class RankInfoServiceTest extends BaseTest {

    @Autowired
    private RankInfoService service;

    @Test
    public void get(){

        RankInfo result = service.getById("1");
    }

    @Test
    public void delete(){

        service.delete("1");
    }

    @Test
    public void add(){
        RankInfo add = new RankInfo();



                    add.setName("name");


                    add.setRank(1);



        service.insert(add);
    }

    @Test
    public void update(){
        RankInfo update = new RankInfo();


                    update.setId("id");

                    update.setName("name");
                    update.setRank(1);


        service.update(update);
    }

    @Test
    public void query(){
        RankInfoSearch search = new RankInfoSearch();


                    search.setName("name");
                    search.setRank(1);


        service.query(search);
    }

    @Test
    public void queryPage(){
        IPage<RankInfo> page = new Page<>(1, 10);

        RankInfoSearch search = new RankInfoSearch();



                    search.setName("name");


                    search.setRank(1);



        service.queryPage(page, search);
    }

}
package com.spyker.commons.service;

import com.spyker.commons.entity.OpenProcessMain;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import com.spyker.commons.search.OpenProcessMainSearch;

/**
 * 流程主表 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenProcessMainService extends IService<OpenProcessMain> {

    // @formatter:off

    List<OpenProcessMain> query(OpenProcessMainSearch search);

    IPage<OpenProcessMain> queryPage(IPage<OpenProcessMain> page, OpenProcessMainSearch search);

    OpenProcessMain get(String id);

    OpenProcessMain insert(OpenProcessMain openProcessMain);

    OpenProcessMain update(OpenProcessMain openProcessMain);

    boolean delete(String id);
}
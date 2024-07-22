package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.OpenProcessStarter;
import com.spyker.commons.search.OpenProcessStarterSearch;

import java.util.List;

/**
 * 流程发起人范围 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenProcessStarterService extends IService<OpenProcessStarter> {

    // @formatter:off

    List<OpenProcessStarter> query(OpenProcessStarterSearch search);

    IPage<OpenProcessStarter> queryPage(
            IPage<OpenProcessStarter> page, OpenProcessStarterSearch search);

    OpenProcessStarter get(String id);

    OpenProcessStarter insert(OpenProcessStarter openProcessStarter);

    OpenProcessStarter update(OpenProcessStarter openProcessStarter);

    boolean delete(String id);
}
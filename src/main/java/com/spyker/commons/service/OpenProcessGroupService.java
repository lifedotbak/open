package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.OpenProcessGroup;
import com.spyker.commons.search.OpenProcessGroupSearch;

import java.util.List;

/**
 * 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenProcessGroupService extends IService<OpenProcessGroup> {

    // @formatter:off

    List<OpenProcessGroup> query(OpenProcessGroupSearch search);

    IPage<OpenProcessGroup> queryPage(IPage<OpenProcessGroup> page, OpenProcessGroupSearch search);

    OpenProcessGroup get(String id);

    OpenProcessGroup insert(OpenProcessGroup openProcessGroup);

    OpenProcessGroup update(OpenProcessGroup openProcessGroup);

    boolean delete(String id);
}
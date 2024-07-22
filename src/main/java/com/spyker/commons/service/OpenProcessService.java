package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.OpenProcess;
import com.spyker.commons.search.OpenProcessSearch;

import java.util.List;

/**
 * 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenProcessService extends IService<OpenProcess> {

    // @formatter:off

    List<OpenProcess> query(OpenProcessSearch search);

    IPage<OpenProcess> queryPage(IPage<OpenProcess> page, OpenProcessSearch search);

    OpenProcess get(String id);

    OpenProcess insert(OpenProcess openProcess);

    OpenProcess update(OpenProcess openProcess);

    boolean delete(String id);
}
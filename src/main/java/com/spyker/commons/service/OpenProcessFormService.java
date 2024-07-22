package com.spyker.commons.service;

import com.spyker.commons.entity.OpenProcessForm;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import com.spyker.commons.search.OpenProcessFormSearch;

/**
 * 流程表单 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenProcessFormService extends IService<OpenProcessForm> {

    // @formatter:off

    List<OpenProcessForm> query(OpenProcessFormSearch search);

    IPage<OpenProcessForm> queryPage(IPage<OpenProcessForm> page, OpenProcessFormSearch search);

    OpenProcessForm get(String id);

    OpenProcessForm insert(OpenProcessForm openProcessForm);

    OpenProcessForm update(OpenProcessForm openProcessForm);

    boolean delete(String id);
}
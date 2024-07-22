package com.spyker.commons.service;

import com.spyker.commons.entity.OpenProcessNodeData;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import com.spyker.commons.search.OpenProcessNodeDataSearch;

/**
 * 流程节点数据 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenProcessNodeDataService extends IService<OpenProcessNodeData> {

    // @formatter:off

    List<OpenProcessNodeData> query(OpenProcessNodeDataSearch search);

    IPage<OpenProcessNodeData> queryPage(
            IPage<OpenProcessNodeData> page, OpenProcessNodeDataSearch search);

    OpenProcessNodeData get(String id);

    OpenProcessNodeData insert(OpenProcessNodeData openProcessNodeData);

    OpenProcessNodeData update(OpenProcessNodeData openProcessNodeData);

    boolean delete(String id);
}
package com.spyker.commons.service;

import com.spyker.commons.entity.OpenProcessInstanceNodeRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import com.spyker.commons.search.OpenProcessInstanceNodeRecordSearch;

/**
 * 流程节点记录 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenProcessInstanceNodeRecordService
        extends IService<OpenProcessInstanceNodeRecord> {

    // @formatter:off

    List<OpenProcessInstanceNodeRecord> query(OpenProcessInstanceNodeRecordSearch search);

    IPage<OpenProcessInstanceNodeRecord> queryPage(
            IPage<OpenProcessInstanceNodeRecord> page, OpenProcessInstanceNodeRecordSearch search);

    OpenProcessInstanceNodeRecord get(String id);

    OpenProcessInstanceNodeRecord insert(
            OpenProcessInstanceNodeRecord openProcessInstanceNodeRecord);

    OpenProcessInstanceNodeRecord update(
            OpenProcessInstanceNodeRecord openProcessInstanceNodeRecord);

    boolean delete(String id);
}
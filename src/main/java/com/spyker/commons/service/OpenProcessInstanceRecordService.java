package com.spyker.commons.service;

import com.spyker.commons.entity.OpenProcessInstanceRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import com.spyker.commons.search.OpenProcessInstanceRecordSearch;

/**
 * 流程记录 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenProcessInstanceRecordService extends IService<OpenProcessInstanceRecord> {

    // @formatter:off

    List<OpenProcessInstanceRecord> query(OpenProcessInstanceRecordSearch search);

    IPage<OpenProcessInstanceRecord> queryPage(
            IPage<OpenProcessInstanceRecord> page, OpenProcessInstanceRecordSearch search);

    OpenProcessInstanceRecord get(String id);

    OpenProcessInstanceRecord insert(OpenProcessInstanceRecord openProcessInstanceRecord);

    OpenProcessInstanceRecord update(OpenProcessInstanceRecord openProcessInstanceRecord);

    boolean delete(String id);
}
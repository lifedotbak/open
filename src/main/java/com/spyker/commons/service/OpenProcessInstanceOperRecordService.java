package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.OpenProcessInstanceOperRecord;
import com.spyker.commons.search.OpenProcessInstanceOperRecordSearch;

import java.util.List;

/**
 * 流程记录 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenProcessInstanceOperRecordService
        extends IService<OpenProcessInstanceOperRecord> {

    // @formatter:off

    List<OpenProcessInstanceOperRecord> query(OpenProcessInstanceOperRecordSearch search);

    IPage<OpenProcessInstanceOperRecord> queryPage(
            IPage<OpenProcessInstanceOperRecord> page, OpenProcessInstanceOperRecordSearch search);

    OpenProcessInstanceOperRecord get(String id);

    OpenProcessInstanceOperRecord insert(
            OpenProcessInstanceOperRecord openProcessInstanceOperRecord);

    OpenProcessInstanceOperRecord update(
            OpenProcessInstanceOperRecord openProcessInstanceOperRecord);

    boolean delete(String id);
}
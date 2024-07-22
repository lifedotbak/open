package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.OpenProcessInstanceAssignUserRecord;
import com.spyker.commons.search.OpenProcessInstanceAssignUserRecordSearch;

import java.util.List;

/**
 * 流程节点记录-执行人 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenProcessInstanceAssignUserRecordService
        extends IService<OpenProcessInstanceAssignUserRecord> {

    // @formatter:off

    List<OpenProcessInstanceAssignUserRecord> query(
            OpenProcessInstanceAssignUserRecordSearch search);

    IPage<OpenProcessInstanceAssignUserRecord> queryPage(
            IPage<OpenProcessInstanceAssignUserRecord> page,
            OpenProcessInstanceAssignUserRecordSearch search);

    OpenProcessInstanceAssignUserRecord get(String id);

    OpenProcessInstanceAssignUserRecord insert(
            OpenProcessInstanceAssignUserRecord openProcessInstanceAssignUserRecord);

    OpenProcessInstanceAssignUserRecord update(
            OpenProcessInstanceAssignUserRecord openProcessInstanceAssignUserRecord);

    boolean delete(String id);
}
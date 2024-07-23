package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessInstanceAssignUserRecord;
import com.spyker.commons.search.SysProcessInstanceAssignUserRecordSearch;

import java.util.List;

/**
 * 流程节点记录-执行人 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
public interface SysProcessInstanceAssignUserRecordService
        extends IService<SysProcessInstanceAssignUserRecord> {

    List<SysProcessInstanceAssignUserRecord> query(SysProcessInstanceAssignUserRecordSearch search);

    IPage<SysProcessInstanceAssignUserRecord> queryPage(
            IPage<SysProcessInstanceAssignUserRecord> page,
            SysProcessInstanceAssignUserRecordSearch search);

    SysProcessInstanceAssignUserRecord get(String id);

    SysProcessInstanceAssignUserRecord insert(
            SysProcessInstanceAssignUserRecord sysProcessInstanceAssignUserRecord);

    SysProcessInstanceAssignUserRecord update(
            SysProcessInstanceAssignUserRecord sysProcessInstanceAssignUserRecord);

    boolean delete(String id);
}
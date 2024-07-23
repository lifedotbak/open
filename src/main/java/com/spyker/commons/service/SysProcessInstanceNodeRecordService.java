package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessInstanceNodeRecord;
import com.spyker.commons.search.SysProcessInstanceNodeRecordSearch;

import java.util.List;

/**
 * 流程节点记录 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
public interface SysProcessInstanceNodeRecordService
        extends IService<SysProcessInstanceNodeRecord> {

    List<SysProcessInstanceNodeRecord> query(SysProcessInstanceNodeRecordSearch search);

    IPage<SysProcessInstanceNodeRecord> queryPage(
            IPage<SysProcessInstanceNodeRecord> page, SysProcessInstanceNodeRecordSearch search);

    SysProcessInstanceNodeRecord get(String id);

    SysProcessInstanceNodeRecord insert(SysProcessInstanceNodeRecord sysProcessInstanceNodeRecord);

    SysProcessInstanceNodeRecord update(SysProcessInstanceNodeRecord sysProcessInstanceNodeRecord);

    boolean delete(String id);
}
package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessInstanceNodeRecord;
import com.spyker.commons.search.SysProcessInstanceNodeRecordSearch;

import java.util.List;

/** 流程节点记录 服务接口 */
public interface SysProcessInstanceNodeRecordService
        extends IService<SysProcessInstanceNodeRecord> {

    boolean delete(String id);

    SysProcessInstanceNodeRecord get(String id);

    SysProcessInstanceNodeRecord insert(SysProcessInstanceNodeRecord sysProcessInstanceNodeRecord);

    List<SysProcessInstanceNodeRecord> query(SysProcessInstanceNodeRecordSearch search);

    IPage<SysProcessInstanceNodeRecord> queryPage(
            IPage<SysProcessInstanceNodeRecord> page, SysProcessInstanceNodeRecordSearch search);

    SysProcessInstanceNodeRecord update(SysProcessInstanceNodeRecord sysProcessInstanceNodeRecord);
}

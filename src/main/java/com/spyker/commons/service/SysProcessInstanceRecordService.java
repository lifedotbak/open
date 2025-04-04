package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessInstanceRecord;
import com.spyker.commons.search.SysProcessInstanceRecordSearch;

import java.util.List;

/**
 * 流程记录 服务接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
public interface SysProcessInstanceRecordService extends IService<SysProcessInstanceRecord> {

    List<SysProcessInstanceRecord> query(SysProcessInstanceRecordSearch search);

    IPage<SysProcessInstanceRecord> queryPage(
            IPage<SysProcessInstanceRecord> page, SysProcessInstanceRecordSearch search);

    SysProcessInstanceRecord get(String id);

    SysProcessInstanceRecord insert(SysProcessInstanceRecord sysProcessInstanceRecord);

    SysProcessInstanceRecord update(SysProcessInstanceRecord sysProcessInstanceRecord);

    boolean delete(String id);
}

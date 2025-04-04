package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessInstanceOperRecord;
import com.spyker.commons.search.SysProcessInstanceOperRecordSearch;

import java.util.List;

/**
 * 流程记录 服务接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
public interface SysProcessInstanceOperRecordService
        extends IService<SysProcessInstanceOperRecord> {

    List<SysProcessInstanceOperRecord> query(SysProcessInstanceOperRecordSearch search);

    IPage<SysProcessInstanceOperRecord> queryPage(
            IPage<SysProcessInstanceOperRecord> page, SysProcessInstanceOperRecordSearch search);

    SysProcessInstanceOperRecord get(String id);

    SysProcessInstanceOperRecord insert(SysProcessInstanceOperRecord sysProcessInstanceOperRecord);

    SysProcessInstanceOperRecord update(SysProcessInstanceOperRecord sysProcessInstanceOperRecord);

    boolean delete(String id);
}

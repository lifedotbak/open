package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessInstanceCopy;
import com.spyker.commons.search.SysProcessInstanceCopySearch;

import java.util.List;

/** 流程抄送数据 服务接口 */
public interface SysProcessInstanceCopyService extends IService<SysProcessInstanceCopy> {

    boolean delete(String id);

    SysProcessInstanceCopy get(String id);

    SysProcessInstanceCopy insert(SysProcessInstanceCopy sysProcessInstanceCopy);

    List<SysProcessInstanceCopy> query(SysProcessInstanceCopySearch search);

    IPage<SysProcessInstanceCopy> queryPage(
            IPage<SysProcessInstanceCopy> page, SysProcessInstanceCopySearch search);

    SysProcessInstanceCopy update(SysProcessInstanceCopy sysProcessInstanceCopy);
}

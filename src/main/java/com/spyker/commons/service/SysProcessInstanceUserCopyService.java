package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessInstanceUserCopy;
import com.spyker.commons.search.SysProcessInstanceUserCopySearch;

import java.util.List;

/** 流程抄送数据--用户和实例唯一值 服务接口 */
public interface SysProcessInstanceUserCopyService extends IService<SysProcessInstanceUserCopy> {

    boolean delete(String id);

    SysProcessInstanceUserCopy get(String id);

    SysProcessInstanceUserCopy insert(SysProcessInstanceUserCopy sysProcessInstanceUserCopy);

    List<SysProcessInstanceUserCopy> query(SysProcessInstanceUserCopySearch search);

    IPage<SysProcessInstanceUserCopy> queryPage(
            IPage<SysProcessInstanceUserCopy> page, SysProcessInstanceUserCopySearch search);

    SysProcessInstanceUserCopy update(SysProcessInstanceUserCopy sysProcessInstanceUserCopy);
}

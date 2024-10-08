package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessInstanceCopy;
import com.spyker.commons.search.SysProcessInstanceCopySearch;

import java.util.List;

/**
 * 流程抄送数据 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
public interface SysProcessInstanceCopyService extends IService<SysProcessInstanceCopy> {

    List<SysProcessInstanceCopy> query(SysProcessInstanceCopySearch search);

    IPage<SysProcessInstanceCopy> queryPage(
            IPage<SysProcessInstanceCopy> page, SysProcessInstanceCopySearch search);

    SysProcessInstanceCopy get(String id);

    SysProcessInstanceCopy insert(SysProcessInstanceCopy sysProcessInstanceCopy);

    SysProcessInstanceCopy update(SysProcessInstanceCopy sysProcessInstanceCopy);

    boolean delete(String id);
}
package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessInstanceUserCopy;
import com.spyker.commons.search.SysProcessInstanceUserCopySearch;

import java.util.List;

/**
 * 流程抄送数据--用户和实例唯一值 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
public interface SysProcessInstanceUserCopyService extends IService<SysProcessInstanceUserCopy> {

    List<SysProcessInstanceUserCopy> query(SysProcessInstanceUserCopySearch search);

    IPage<SysProcessInstanceUserCopy> queryPage(
            IPage<SysProcessInstanceUserCopy> page, SysProcessInstanceUserCopySearch search);

    SysProcessInstanceUserCopy get(String id);

    SysProcessInstanceUserCopy insert(SysProcessInstanceUserCopy sysProcessInstanceUserCopy);

    SysProcessInstanceUserCopy update(SysProcessInstanceUserCopy sysProcessInstanceUserCopy);

    boolean delete(String id);
}
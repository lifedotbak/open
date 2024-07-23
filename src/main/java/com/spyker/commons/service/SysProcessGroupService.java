package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessGroup;
import com.spyker.commons.search.SysProcessGroupSearch;

import java.util.List;

/**
 * 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
public interface SysProcessGroupService extends IService<SysProcessGroup> {

    List<SysProcessGroup> query(SysProcessGroupSearch search);

    IPage<SysProcessGroup> queryPage(IPage<SysProcessGroup> page, SysProcessGroupSearch search);

    SysProcessGroup get(String id);

    SysProcessGroup insert(SysProcessGroup sysProcessGroup);

    SysProcessGroup update(SysProcessGroup sysProcessGroup);

    boolean delete(String id);
}
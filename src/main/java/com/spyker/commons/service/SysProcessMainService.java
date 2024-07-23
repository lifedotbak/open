package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessMain;
import com.spyker.commons.search.SysProcessMainSearch;

import java.util.List;

/**
 * 流程主表 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
public interface SysProcessMainService extends IService<SysProcessMain> {

    List<SysProcessMain> query(SysProcessMainSearch search);

    IPage<SysProcessMain> queryPage(IPage<SysProcessMain> page, SysProcessMainSearch search);

    SysProcessMain get(String id);

    SysProcessMain insert(SysProcessMain sysProcessMain);

    SysProcessMain update(SysProcessMain sysProcessMain);

    boolean delete(String id);
}
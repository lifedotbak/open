package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessMain;
import com.spyker.commons.search.SysProcessMainSearch;

import java.util.List;

/** 流程主表 服务接口 */
public interface SysProcessMainService extends IService<SysProcessMain> {

    boolean delete(String id);

    SysProcessMain get(String id);

    SysProcessMain insert(SysProcessMain sysProcessMain);

    List<SysProcessMain> query(SysProcessMainSearch search);

    IPage<SysProcessMain> queryPage(IPage<SysProcessMain> page, SysProcessMainSearch search);

    SysProcessMain update(SysProcessMain sysProcessMain);
}

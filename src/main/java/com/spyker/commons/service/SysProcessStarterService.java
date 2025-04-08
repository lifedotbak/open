package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessStarter;
import com.spyker.commons.search.SysProcessStarterSearch;

import java.util.List;

/** 流程发起人范围 服务接口 */
public interface SysProcessStarterService extends IService<SysProcessStarter> {

    boolean delete(String id);

    SysProcessStarter get(String id);

    SysProcessStarter insert(SysProcessStarter sysProcessStarter);

    List<SysProcessStarter> query(SysProcessStarterSearch search);

    IPage<SysProcessStarter> queryPage(
            IPage<SysProcessStarter> page, SysProcessStarterSearch search);

    SysProcessStarter update(SysProcessStarter sysProcessStarter);
}

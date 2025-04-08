package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcess;
import com.spyker.commons.search.SysProcessSearch;

import java.util.List;

/** 服务接口 */
public interface SysProcessService extends IService<SysProcess> {

    boolean delete(String id);

    SysProcess get(String id);

    SysProcess insert(SysProcess sysProcess);

    List<SysProcess> query(SysProcessSearch search);

    IPage<SysProcess> queryPage(IPage<SysProcess> page, SysProcessSearch search);

    SysProcess update(SysProcess sysProcess);
}

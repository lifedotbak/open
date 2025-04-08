package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysDeptLeader;
import com.spyker.commons.search.SysDeptLeaderSearch;

import java.util.List;

/** 部门-主管表 服务接口 */
public interface SysDeptLeaderService extends IService<SysDeptLeader> {

    boolean delete(String id);

    SysDeptLeader get(String id);

    SysDeptLeader insert(SysDeptLeader sysDeptLeader);

    List<SysDeptLeader> query(SysDeptLeaderSearch search);

    IPage<SysDeptLeader> queryPage(IPage<SysDeptLeader> page, SysDeptLeaderSearch search);

    SysDeptLeader update(SysDeptLeader sysDeptLeader);
}

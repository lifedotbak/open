package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysDeptLeader;
import com.spyker.commons.search.SysDeptLeaderSearch;

import java.util.List;

/**
 * 部门-主管表 服务接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
public interface SysDeptLeaderService extends IService<SysDeptLeader> {

    List<SysDeptLeader> query(SysDeptLeaderSearch search);

    IPage<SysDeptLeader> queryPage(IPage<SysDeptLeader> page, SysDeptLeaderSearch search);

    SysDeptLeader get(String id);

    SysDeptLeader insert(SysDeptLeader sysDeptLeader);

    SysDeptLeader update(SysDeptLeader sysDeptLeader);

    boolean delete(String id);
}

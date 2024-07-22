package com.spyker.commons.service;

import com.spyker.commons.entity.OpenDeptLeader;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import com.spyker.commons.search.OpenDeptLeaderSearch;

/**
 * 部门-主管表 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenDeptLeaderService extends IService<OpenDeptLeader> {

    // @formatter:off

    List<OpenDeptLeader> query(OpenDeptLeaderSearch search);

    IPage<OpenDeptLeader> queryPage(IPage<OpenDeptLeader> page, OpenDeptLeaderSearch search);

    OpenDeptLeader get(String id);

    OpenDeptLeader insert(OpenDeptLeader openDeptLeader);

    OpenDeptLeader update(OpenDeptLeader openDeptLeader);

    boolean delete(String id);
}
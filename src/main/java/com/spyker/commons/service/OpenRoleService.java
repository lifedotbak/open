package com.spyker.commons.service;

import com.spyker.commons.entity.OpenRole;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import com.spyker.commons.search.OpenRoleSearch;

/**
 * 角色 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenRoleService extends IService<OpenRole> {

    // @formatter:off

    List<OpenRole> query(OpenRoleSearch search);

    IPage<OpenRole> queryPage(IPage<OpenRole> page, OpenRoleSearch search);

    OpenRole get(String id);

    OpenRole insert(OpenRole openRole);

    OpenRole update(OpenRole openRole);

    boolean delete(String id);
}
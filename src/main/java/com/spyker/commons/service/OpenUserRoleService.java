package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.OpenUserRole;
import com.spyker.commons.search.OpenUserRoleSearch;

import java.util.List;

/**
 * 用户-角色 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenUserRoleService extends IService<OpenUserRole> {

    // @formatter:off

    List<OpenUserRole> query(OpenUserRoleSearch search);

    IPage<OpenUserRole> queryPage(IPage<OpenUserRole> page, OpenUserRoleSearch search);

    OpenUserRole get(String id);

    OpenUserRole insert(OpenUserRole openUserRole);

    OpenUserRole update(OpenUserRole openUserRole);

    boolean delete(String id);
}
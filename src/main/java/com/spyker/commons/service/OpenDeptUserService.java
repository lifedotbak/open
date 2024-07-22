package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.OpenDeptUser;
import com.spyker.commons.search.OpenDeptUserSearch;

import java.util.List;

/**
 * 部门-主管表 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenDeptUserService extends IService<OpenDeptUser> {

    // @formatter:off

    List<OpenDeptUser> query(OpenDeptUserSearch search);

    IPage<OpenDeptUser> queryPage(IPage<OpenDeptUser> page, OpenDeptUserSearch search);

    OpenDeptUser get(String id);

    OpenDeptUser insert(OpenDeptUser openDeptUser);

    OpenDeptUser update(OpenDeptUser openDeptUser);

    boolean delete(String id);
}
package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.OpenUser;
import com.spyker.commons.search.OpenUserSearch;

import java.util.List;

/**
 * 用户表 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenUserService extends IService<OpenUser> {

    // @formatter:off

    List<OpenUser> query(OpenUserSearch search);

    IPage<OpenUser> queryPage(IPage<OpenUser> page, OpenUserSearch search);

    OpenUser get(String id);

    OpenUser insert(OpenUser openUser);

    OpenUser update(OpenUser openUser);

    boolean delete(String id);
}
package com.spyker.commons.service;

import com.spyker.commons.entity.SysUserPost;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import com.spyker.commons.search.SysUserPostSearch;

/**
 * 用户与岗位关联表 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface SysUserPostService extends IService<SysUserPost> {

    // @formatter:off

    List<SysUserPost> query(SysUserPostSearch search);

    IPage<SysUserPost> queryPage(IPage<SysUserPost> page, SysUserPostSearch search);

    SysUserPost get(String id);

    SysUserPost insert(SysUserPost sysUserPost);

    SysUserPost update(SysUserPost sysUserPost);

    boolean delete(String id);
}
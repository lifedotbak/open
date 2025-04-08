package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysUserPost;
import com.spyker.commons.search.SysUserPostSearch;

import java.util.List;

/** 用户与岗位关联表 服务接口 */
public interface SysUserPostService extends IService<SysUserPost> {

    boolean delete(String id);

    SysUserPost get(String id);

    SysUserPost insert(SysUserPost sysUserPost);

    List<SysUserPost> query(SysUserPostSearch search);

    IPage<SysUserPost> queryPage(IPage<SysUserPost> page, SysUserPostSearch search);

    SysUserPost update(SysUserPost sysUserPost);
}

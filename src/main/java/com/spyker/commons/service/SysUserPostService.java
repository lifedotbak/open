package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysUserPost;
import com.spyker.commons.search.SysUserPostSearch;

import java.util.List;

/**
 * 用户与岗位关联表 服务接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
public interface SysUserPostService extends IService<SysUserPost> {

    List<SysUserPost> query(SysUserPostSearch search);

    IPage<SysUserPost> queryPage(IPage<SysUserPost> page, SysUserPostSearch search);

    SysUserPost get(String id);

    SysUserPost insert(SysUserPost sysUserPost);

    SysUserPost update(SysUserPost sysUserPost);

    boolean delete(String id);
}

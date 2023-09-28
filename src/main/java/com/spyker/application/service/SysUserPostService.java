package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.application.entity.SysUserPost;
import com.spyker.application.search.SysUserPostSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 用户与岗位关联表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysUserPostService extends IService<SysUserPost> {

    List<SysUserPost> query(SysUserPostSearch search);

    IPage<SysUserPost> queryPage(IPage<SysUserPost> page, SysUserPostSearch search);

    SysUserPost get(String id);

    RestResponse<?> insert(SysUserPost SysUserPost);

    RestResponse<?> update(SysUserPost SysUserPost);

    RestResponse<?> delete(String id);

}
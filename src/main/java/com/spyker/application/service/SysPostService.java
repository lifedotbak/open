package com.spyker.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.application.entity.SysPost;
import com.spyker.application.search.SysPostSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * <p>
 * 岗位信息表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysPostService extends IService<SysPost> {

    List<SysPost> query(SysPostSearch search);

    IPage<SysPost> queryPage(IPage<SysPost> page, SysPostSearch search);

    SysPost get(String id);

    RestResponse<?> insert(SysPost SysPost);

    RestResponse<?> update(SysPost SysPost);

    RestResponse<?> delete(String id);

}
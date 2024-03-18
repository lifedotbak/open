package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysPost;
import com.spyker.commons.search.SysPostSearch;
import com.spyker.framework.response.RestResponse;

import java.util.List;

/**
 * 岗位信息表 服务类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysPostService extends IService<SysPost> {

    List<SysPost> query(SysPostSearch search);

    IPage<SysPost> queryPage(IPage<SysPost> page, SysPostSearch search);

    SysPost get(String id);

    RestResponse<?> insert(SysPost sysPost);

    RestResponse<?> update(SysPost sysPost);

    RestResponse<?> delete(String id);
}
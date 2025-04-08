package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysPost;
import com.spyker.commons.search.SysPostSearch;
import com.spyker.framework.web.response.RestResponse;

import java.util.List;

/** 岗位信息表 服务类 */
public interface SysPostService extends IService<SysPost> {

    RestResponse<?> delete(String id);

    SysPost get(String id);

    RestResponse<?> insert(SysPost sysPost);

    List<SysPost> query(SysPostSearch search);

    IPage<SysPost> queryPage(IPage<SysPost> page, SysPostSearch search);

    RestResponse<?> update(SysPost sysPost);
}

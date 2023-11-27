package com.spyker.commons.service;

import com.spyker.commons.entity.RInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import com.spyker.framework.response.RestResponse;

import com.spyker.commons.search.RInfoSearch;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-11-24
 */
public interface RInfoService extends IService<RInfo> {

    List<RInfo> query(RInfoSearch search);

    IPage<RInfo> queryPage(IPage<RInfo> page, RInfoSearch search);

    RInfo get(String id);

    boolean insert(RInfo RInfo);

    boolean update(RInfo RInfo);

    boolean delete(String id);

}
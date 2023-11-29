package com.spyker.commons.service;

import com.spyker.commons.entity.RankInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import com.spyker.framework.response.RestResponse;

import com.spyker.commons.search.RankInfoSearch;

/**
 * <p>
 * Rank表 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-11-28
 */
public interface RankInfoService extends IService<RankInfo> {

    List<RankInfo> query(RankInfoSearch search);

    IPage<RankInfo> queryPage(IPage<RankInfo> page, RankInfoSearch search);

    RankInfo get(String id);

    boolean insert(RankInfo RankInfo);

    boolean update(RankInfo RankInfo);

    boolean delete(String id);

}
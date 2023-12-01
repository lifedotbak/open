package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.RankInfo;
import com.spyker.commons.search.RankInfoSearch;

import java.util.List;

/**
 * <p>
 * Rank表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-11-28
 */
public interface RankInfoMapper extends BaseMapper<RankInfo> {

    List<RankInfo> query(RankInfoSearch search);

    IPage<RankInfo> queryPage(IPage<RankInfo> page, RankInfoSearch search);

}
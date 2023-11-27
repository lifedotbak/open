package com.spyker.commons.mapper;

import com.spyker.commons.entity.RInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import com.spyker.commons.search.RInfoSearch;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-11-24
 */
public interface RInfoMapper extends BaseMapper<RInfo> {
  
    List<RInfo> query(RInfoSearch search);
    
    IPage<RInfo> queryPage(IPage<RInfo> page, RInfoSearch search);
  
}

package com.spyker.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.application.entity.SysConfig;
import com.spyker.application.search.SysConfigSearch;

import java.util.List;

/**
 * <p>
 * 参数配置表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    List<SysConfig> query(SysConfigSearch search);

    IPage<SysConfig> queryPage(IPage<SysConfig> page, SysConfigSearch search);

}
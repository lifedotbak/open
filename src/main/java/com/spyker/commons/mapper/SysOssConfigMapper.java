package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysOssConfig;
import com.spyker.commons.search.SysOssConfigSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 对象存储配置表 Mapper 接口 */
@Mapper
public interface SysOssConfigMapper extends BaseMapper<SysOssConfig> {

    List<SysOssConfig> query(SysOssConfigSearch search);

    IPage<SysOssConfig> queryPage(IPage<SysOssConfig> page, SysOssConfigSearch search);
}

package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysConfig;
import com.spyker.commons.search.SysConfigSearch;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/** 参数配置表 Mapper 接口 */
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    @Select("select * from sys_config where config_key = #{confiKey} limit 1")
    SysConfig getByConfigKey(String confiKey);

    List<SysConfig> query(SysConfigSearch search);

    IPage<SysConfig> queryPage(IPage<SysConfig> page, SysConfigSearch search);
}

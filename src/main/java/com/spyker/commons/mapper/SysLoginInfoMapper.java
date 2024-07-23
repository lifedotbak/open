package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysLoginInfo;
import com.spyker.commons.search.SysLoginInfoSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统访问记录 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Mapper
public interface SysLoginInfoMapper extends BaseMapper<SysLoginInfo> {

    List<SysLoginInfo> query(SysLoginInfoSearch search);

    IPage<SysLoginInfo> queryPage(IPage<SysLoginInfo> page, SysLoginInfoSearch search);
}
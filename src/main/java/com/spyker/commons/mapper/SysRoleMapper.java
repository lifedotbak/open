package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysRole;
import com.spyker.commons.search.SysRoleSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 角色信息表 Mapper 接口 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> query(SysRoleSearch search);

    IPage<SysRole> queryPage(IPage<SysRole> page, SysRoleSearch search);
}

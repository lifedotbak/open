package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysUserRole;
import com.spyker.commons.search.SysUserRoleSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户-角色 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    List<SysUserRole> query(SysUserRoleSearch search);

    IPage<SysUserRole> queryPage(IPage<SysUserRole> page, SysUserRoleSearch search);
}
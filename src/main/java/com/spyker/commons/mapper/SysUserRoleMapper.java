package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysUserRole;
import com.spyker.commons.search.SysUserRoleSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户和角色关联表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    // @formatter:off

    List<SysUserRole> query(SysUserRoleSearch search);

    IPage<SysUserRole> queryPage(IPage<SysUserRole> page, SysUserRoleSearch search);
}
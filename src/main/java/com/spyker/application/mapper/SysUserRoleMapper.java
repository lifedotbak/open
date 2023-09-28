package com.spyker.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.application.entity.SysUserRole;
import com.spyker.application.search.SysUserRoleSearch;

import java.util.List;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    List<SysUserRole> query(SysUserRoleSearch search);

    IPage<SysUserRole> queryPage(IPage<SysUserRole> page, SysUserRoleSearch search);

}
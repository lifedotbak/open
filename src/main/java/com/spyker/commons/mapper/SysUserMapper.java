package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysUser;
import com.spyker.commons.search.SysUserSearch;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> query(SysUserSearch search);

    IPage<SysUser> queryPage(IPage<SysUser> page, SysUserSearch search);

    SysUser getUserByName(String userName);

    List<String> queryRolesById(String userId);

    List<String> queryPermsById(String userId);

}
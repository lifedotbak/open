package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysUser;
import com.spyker.commons.search.SysUserSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 用户信息表 Mapper 接口 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser getUserByName(String userName);

    List<SysUser> query(SysUserSearch search);

    IPage<SysUser> queryPage(IPage<SysUser> page, SysUserSearch search);

    List<String> queryPermsById(String userId);

    List<String> queryRolesById(String userId);
}

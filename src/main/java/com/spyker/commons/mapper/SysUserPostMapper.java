package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysUserPost;
import com.spyker.commons.search.SysUserPostSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与岗位关联表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Mapper
public interface SysUserPostMapper extends BaseMapper<SysUserPost> {

    List<SysUserPost> query(SysUserPostSearch search);

    IPage<SysUserPost> queryPage(IPage<SysUserPost> page, SysUserPostSearch search);
}
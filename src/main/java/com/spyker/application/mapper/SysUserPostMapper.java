package com.spyker.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.application.entity.SysUserPost;
import com.spyker.application.search.SysUserPostSearch;

import java.util.List;

/**
 * <p>
 * 用户与岗位关联表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysUserPostMapper extends BaseMapper<SysUserPost> {

    List<SysUserPost> query(SysUserPostSearch search);

    IPage<SysUserPost> queryPage(IPage<SysUserPost> page, SysUserPostSearch search);

}
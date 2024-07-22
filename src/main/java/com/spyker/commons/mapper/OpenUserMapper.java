package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenUser;
import com.spyker.commons.search.OpenUserSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenUserMapper extends BaseMapper<OpenUser> {

    // @formatter:off

    List<OpenUser> query(OpenUserSearch search);

    IPage<OpenUser> queryPage(IPage<OpenUser> page, OpenUserSearch search);
}
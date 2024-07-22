package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenDeptLeader;
import com.spyker.commons.search.OpenDeptLeaderSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门-主管表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenDeptLeaderMapper extends BaseMapper<OpenDeptLeader> {

    // @formatter:off

    List<OpenDeptLeader> query(OpenDeptLeaderSearch search);

    IPage<OpenDeptLeader> queryPage(IPage<OpenDeptLeader> page, OpenDeptLeaderSearch search);
}
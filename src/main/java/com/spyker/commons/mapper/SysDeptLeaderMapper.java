package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysDeptLeader;
import com.spyker.commons.search.SysDeptLeaderSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门-主管表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Mapper
public interface SysDeptLeaderMapper extends BaseMapper<SysDeptLeader> {

    List<SysDeptLeader> query(SysDeptLeaderSearch search);

    IPage<SysDeptLeader> queryPage(IPage<SysDeptLeader> page, SysDeptLeaderSearch search);
}
package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenDept;
import com.spyker.commons.search.OpenDeptSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门表 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenDeptMapper extends BaseMapper<OpenDept> {

    // @formatter:off

    List<OpenDept> query(OpenDeptSearch search);

    IPage<OpenDept> queryPage(IPage<OpenDept> page, OpenDeptSearch search);
}
package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysDept;
import com.spyker.commons.search.SysDeptSearch;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    List<SysDept> query(SysDeptSearch search);

    IPage<SysDept> queryPage(IPage<SysDept> page, SysDeptSearch search);

}
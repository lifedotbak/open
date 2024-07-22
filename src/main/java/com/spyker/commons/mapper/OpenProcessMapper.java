package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenProcess;
import com.spyker.commons.search.OpenProcessSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenProcessMapper extends BaseMapper<OpenProcess> {

    // @formatter:off

    List<OpenProcess> query(OpenProcessSearch search);

    IPage<OpenProcess> queryPage(IPage<OpenProcess> page, OpenProcessSearch search);
}
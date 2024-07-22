package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenProcessStarter;
import com.spyker.commons.search.OpenProcessStarterSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程发起人范围 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenProcessStarterMapper extends BaseMapper<OpenProcessStarter> {

    // @formatter:off

    List<OpenProcessStarter> query(OpenProcessStarterSearch search);

    IPage<OpenProcessStarter> queryPage(
            IPage<OpenProcessStarter> page, OpenProcessStarterSearch search);
}
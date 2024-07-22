package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenProcessNodeData;
import com.spyker.commons.search.OpenProcessNodeDataSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程节点数据 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenProcessNodeDataMapper extends BaseMapper<OpenProcessNodeData> {

    // @formatter:off

    List<OpenProcessNodeData> query(OpenProcessNodeDataSearch search);

    IPage<OpenProcessNodeData> queryPage(
            IPage<OpenProcessNodeData> page, OpenProcessNodeDataSearch search);
}
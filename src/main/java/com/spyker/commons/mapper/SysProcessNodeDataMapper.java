package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysProcessNodeData;
import com.spyker.commons.search.SysProcessNodeDataSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程节点数据 Mapper 接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Mapper
public interface SysProcessNodeDataMapper extends BaseMapper<SysProcessNodeData> {

    List<SysProcessNodeData> query(SysProcessNodeDataSearch search);

    IPage<SysProcessNodeData> queryPage(
            IPage<SysProcessNodeData> page, SysProcessNodeDataSearch search);
}

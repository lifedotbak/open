package com.spyker.flowable.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.spyker.flowable.biz.entity.Process;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Mapper 接口
 *
 * @author xiaoge
 * @since 2023-05-25
 */
public interface ProcessMapper extends BaseMapper<Process> {

    @Select("select * from  process where flow_id=#{flowId}")
    Process selectByFlowId(@Param("flowId") String flowId);
}
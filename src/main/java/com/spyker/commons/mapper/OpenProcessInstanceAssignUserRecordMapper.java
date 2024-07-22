package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenProcessInstanceAssignUserRecord;
import com.spyker.commons.search.OpenProcessInstanceAssignUserRecordSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程节点记录-执行人 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenProcessInstanceAssignUserRecordMapper
        extends BaseMapper<OpenProcessInstanceAssignUserRecord> {

    // @formatter:off

    List<OpenProcessInstanceAssignUserRecord> query(
            OpenProcessInstanceAssignUserRecordSearch search);

    IPage<OpenProcessInstanceAssignUserRecord> queryPage(
            IPage<OpenProcessInstanceAssignUserRecord> page,
            OpenProcessInstanceAssignUserRecordSearch search);
}
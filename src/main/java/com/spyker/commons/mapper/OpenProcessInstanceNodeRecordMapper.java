package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenProcessInstanceNodeRecord;
import com.spyker.commons.search.OpenProcessInstanceNodeRecordSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程节点记录 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenProcessInstanceNodeRecordMapper
        extends BaseMapper<OpenProcessInstanceNodeRecord> {

    // @formatter:off

    List<OpenProcessInstanceNodeRecord> query(OpenProcessInstanceNodeRecordSearch search);

    IPage<OpenProcessInstanceNodeRecord> queryPage(
            IPage<OpenProcessInstanceNodeRecord> page, OpenProcessInstanceNodeRecordSearch search);
}
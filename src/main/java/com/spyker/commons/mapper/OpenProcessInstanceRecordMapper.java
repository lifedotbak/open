package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenProcessInstanceRecord;
import com.spyker.commons.search.OpenProcessInstanceRecordSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程记录 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenProcessInstanceRecordMapper extends BaseMapper<OpenProcessInstanceRecord> {

    // @formatter:off

    List<OpenProcessInstanceRecord> query(OpenProcessInstanceRecordSearch search);

    IPage<OpenProcessInstanceRecord> queryPage(
            IPage<OpenProcessInstanceRecord> page, OpenProcessInstanceRecordSearch search);
}
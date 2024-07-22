package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.OpenProcessInstanceOperRecord;
import com.spyker.commons.search.OpenProcessInstanceOperRecordSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程记录 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Mapper
public interface OpenProcessInstanceOperRecordMapper
        extends BaseMapper<OpenProcessInstanceOperRecord> {

    // @formatter:off

    List<OpenProcessInstanceOperRecord> query(OpenProcessInstanceOperRecordSearch search);

    IPage<OpenProcessInstanceOperRecord> queryPage(
            IPage<OpenProcessInstanceOperRecord> page, OpenProcessInstanceOperRecordSearch search);
}
package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysProcessInstanceOperRecord;
import com.spyker.commons.search.SysProcessInstanceOperRecordSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 流程记录 Mapper 接口 */
@Mapper
public interface SysProcessInstanceOperRecordMapper
        extends BaseMapper<SysProcessInstanceOperRecord> {

    List<SysProcessInstanceOperRecord> query(SysProcessInstanceOperRecordSearch search);

    IPage<SysProcessInstanceOperRecord> queryPage(
            IPage<SysProcessInstanceOperRecord> page, SysProcessInstanceOperRecordSearch search);
}

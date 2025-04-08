package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysProcessInstanceNodeRecord;
import com.spyker.commons.search.SysProcessInstanceNodeRecordSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 流程节点记录 Mapper 接口 */
@Mapper
public interface SysProcessInstanceNodeRecordMapper
        extends BaseMapper<SysProcessInstanceNodeRecord> {

    List<SysProcessInstanceNodeRecord> query(SysProcessInstanceNodeRecordSearch search);

    IPage<SysProcessInstanceNodeRecord> queryPage(
            IPage<SysProcessInstanceNodeRecord> page, SysProcessInstanceNodeRecordSearch search);
}

package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysProcessInstanceRecord;
import com.spyker.commons.search.SysProcessInstanceRecordSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程记录 Mapper 接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Mapper
public interface SysProcessInstanceRecordMapper extends BaseMapper<SysProcessInstanceRecord> {

    List<SysProcessInstanceRecord> query(SysProcessInstanceRecordSearch search);

    IPage<SysProcessInstanceRecord> queryPage(
            IPage<SysProcessInstanceRecord> page, SysProcessInstanceRecordSearch search);
}

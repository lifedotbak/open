package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysProcessInstanceAssignUserRecord;
import com.spyker.commons.search.SysProcessInstanceAssignUserRecordSearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程节点记录-执行人 Mapper 接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Mapper
public interface SysProcessInstanceAssignUserRecordMapper
        extends BaseMapper<SysProcessInstanceAssignUserRecord> {

    List<SysProcessInstanceAssignUserRecord> query(SysProcessInstanceAssignUserRecordSearch search);

    IPage<SysProcessInstanceAssignUserRecord> queryPage(
            IPage<SysProcessInstanceAssignUserRecord> page,
            SysProcessInstanceAssignUserRecordSearch search);
}

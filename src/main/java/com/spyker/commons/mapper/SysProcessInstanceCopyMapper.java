package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysProcessInstanceCopy;
import com.spyker.commons.search.SysProcessInstanceCopySearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程抄送数据 Mapper 接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Mapper
public interface SysProcessInstanceCopyMapper extends BaseMapper<SysProcessInstanceCopy> {

    List<SysProcessInstanceCopy> query(SysProcessInstanceCopySearch search);

    IPage<SysProcessInstanceCopy> queryPage(
            IPage<SysProcessInstanceCopy> page, SysProcessInstanceCopySearch search);
}
package com.spyker.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spyker.commons.entity.SysProcessInstanceUserCopy;
import com.spyker.commons.search.SysProcessInstanceUserCopySearch;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程抄送数据--用户和实例唯一值 Mapper 接口
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Mapper
public interface SysProcessInstanceUserCopyMapper extends BaseMapper<SysProcessInstanceUserCopy> {

    List<SysProcessInstanceUserCopy> query(SysProcessInstanceUserCopySearch search);

    IPage<SysProcessInstanceUserCopy> queryPage(
            IPage<SysProcessInstanceUserCopy> page, SysProcessInstanceUserCopySearch search);
}

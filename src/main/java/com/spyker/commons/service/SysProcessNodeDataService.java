package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessNodeData;
import com.spyker.commons.search.SysProcessNodeDataSearch;

import java.util.List;

/** 流程节点数据 服务接口 */
public interface SysProcessNodeDataService extends IService<SysProcessNodeData> {

    boolean delete(String id);

    SysProcessNodeData get(String id);

    SysProcessNodeData insert(SysProcessNodeData sysProcessNodeData);

    List<SysProcessNodeData> query(SysProcessNodeDataSearch search);

    IPage<SysProcessNodeData> queryPage(
            IPage<SysProcessNodeData> page, SysProcessNodeDataSearch search);

    SysProcessNodeData update(SysProcessNodeData sysProcessNodeData);
}

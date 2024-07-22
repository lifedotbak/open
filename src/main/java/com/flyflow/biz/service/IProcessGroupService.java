package com.flyflow.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flyflow.biz.entity.ProcessGroup;
import com.flyflow.common.dto.R;

import java.util.List;

/**
 * 服务类
 *
 * @author Vincent
 * @since 2023-05-25
 */
public interface IProcessGroupService extends IService<ProcessGroup> {
    /**
     * 组列表
     *
     * @return
     */
    R<List<ProcessGroup>> queryList();

    /**
     * 新增流程分组
     *
     * @param processGroup 分组名
     * @return 添加结果
     */
    R create(ProcessGroup processGroup);

    /**
     * 上移
     *
     * @param processGroup
     * @return
     */
    R topSort(ProcessGroup processGroup);

    /**
     * 下移
     *
     * @param processGroup
     * @return
     */
    R bottomSort(ProcessGroup processGroup);

    /**
     * 修改组
     *
     * @param processGroup
     * @return
     */
    R edit(ProcessGroup processGroup);

    /**
     * 删除分组
     *
     * @param id
     * @return
     */
    R delete(long id);
}
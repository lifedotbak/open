package com.spyker.flowable.biz.service;

import com.github.yulichang.base.MPJBaseService;
import com.spyker.flowable.biz.entity.Dept;
import com.spyker.flowable.biz.vo.DeptVO;
import com.spyker.flowable.common.dto.R;

/**
 * 部门表 服务类
 *
 * @author xiaoge
 * @since 2023-05-05
 */
public interface IDeptService extends MPJBaseService<Dept> {

    /**
     * 创建部门
     *
     * @param dept
     * @return
     */
    R create(DeptVO deptVO);

    /**
     * 修改部门
     *
     * @param deptVO
     * @return
     */
    R updateDept(DeptVO deptVO);
}
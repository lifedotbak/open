package cc.flyflow.biz.service;

import cc.flyflow.biz.entity.Dept;
import cc.flyflow.biz.vo.DeptVO;
import cc.flyflow.common.dto.R;
import com.github.yulichang.base.MPJBaseService;

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
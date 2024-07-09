package cc.flyflow.biz.service.impl;

import cc.flyflow.biz.api.ApiStrategyFactory;
import cc.flyflow.biz.entity.Dept;
import cc.flyflow.biz.entity.DeptLeader;
import cc.flyflow.biz.mapper.DeptMapper;
import cc.flyflow.biz.service.IDeptLeaderService;
import cc.flyflow.biz.service.IDeptService;
import cc.flyflow.biz.utils.DataUtil;
import cc.flyflow.biz.vo.DeptVO;
import cc.flyflow.common.dto.R;
import cc.flyflow.common.dto.flow.NodeUser;
import cc.flyflow.common.dto.third.DeptDto;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门表 服务实现类
 *
 * @author xiaoge
 * @since 2023-05-05
 */
@Service
public class DeptServiceImpl extends MPJBaseServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Resource private IDeptLeaderService deptLeaderService;

    /**
     * 创建部门
     *
     * @param deptVO
     * @return
     */
    @Transactional
    @Override
    public R create(DeptVO deptVO) {

        this.save(deptVO);
        for (NodeUser nodeUser : deptVO.getLeaderUser()) {
            DeptLeader deptLeader = new DeptLeader();
            deptLeader.setDeptId(String.valueOf(deptVO.getId()));
            deptLeader.setUserId(nodeUser.getId());
            deptLeaderService.save(deptLeader);
        }
        return R.success();
    }

    @Transactional
    @Override
    public R updateDept(DeptVO deptVO) {
        List<DeptDto> allDept = ApiStrategyFactory.getStrategy().loadAllDept(null);

        List<DeptDto> deptList =
                DataUtil.selectChildrenByDept(String.valueOf(deptVO.getId()), allDept);

        boolean b = deptList.stream().anyMatch(w -> w.getId().equals(deptVO.getParentId()));
        if (b) {
            return R.fail("当前部门的父级部门不能是当前部门或者当前部门的子级部门");
        }

        this.updateById(deptVO);

        deptLeaderService.lambdaUpdate().eq(DeptLeader::getDeptId, deptVO.getId()).remove();
        for (NodeUser nodeUser : deptVO.getLeaderUser()) {
            DeptLeader deptLeader = new DeptLeader();
            deptLeader.setDeptId(String.valueOf(deptVO.getId()));
            deptLeader.setUserId(nodeUser.getId());
            deptLeaderService.save(deptLeader);
        }

        return R.success("修改成功");
    }
}
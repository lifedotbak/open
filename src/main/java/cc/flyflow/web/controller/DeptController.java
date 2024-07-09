package cc.flyflow.web.controller;

import cc.flyflow.biz.entity.Dept;
import cc.flyflow.biz.service.IDeptService;
import cc.flyflow.biz.service.IOrgService;
import cc.flyflow.biz.vo.DeptVO;
import cc.flyflow.common.dto.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/** 部门接口控制器 */
@RestController
@RequestMapping(value = {"dept"})
public class DeptController {

    @Autowired private IDeptService deptService;

    @Resource private IOrgService orgService;

    /**
     * 创建部门
     *
     * @param deptVO 部门对象
     * @return
     */
    @PostMapping("create")
    public R create(@RequestBody DeptVO deptVO) {
        return deptService.create(deptVO);
    }

    /**
     * 修改部门
     *
     * @param deptVO 部门对象
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody DeptVO deptVO) {
        return deptService.updateDept(deptVO);
    }

    /**
     * 创建部门
     *
     * @param dept 部门对象
     * @return
     */
    @DeleteMapping("delete")
    public R delete(@RequestBody Dept dept) {
        return orgService.delete(dept);
    }
}
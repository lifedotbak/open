package cc.flyflow.biz.service;

import cc.flyflow.biz.entity.Dept;
import cc.flyflow.biz.entity.User;
import cc.flyflow.biz.vo.OrgDataVo;
import cc.flyflow.biz.vo.OrgSelectShowVo;
import cc.flyflow.common.dto.R;

import java.util.List;

public interface IOrgService {

    /**
     * 查询组织架构树
     *
     * @param deptId 部门id
     * @param type 只查询部门架构
     * @param showLeave 是否显示离职员工
     * @return 组织架构树数据
     */
    R<OrgSelectShowVo> getOrgTreeData(String deptId, String type, Boolean showLeave);

    /**
     * 查询所有的组织架构 并树形显示
     *
     * @return
     */
    R getOrgTreeDataAll(String keywords, Integer status);

    /**
     * 模糊搜索用户
     *
     * @param userName 用户名/拼音/首字母
     * @return 匹配到的用户
     */
    R<List<OrgDataVo>> getOrgTreeUser(String userName);

    /**
     * 删除部门
     *
     * @param dept
     * @return
     */
    R delete(Dept dept);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    R getUserDetail(String userId);

    /**
     * 用户离职
     *
     * @param user
     * @return
     */
    R delete(User user);
}
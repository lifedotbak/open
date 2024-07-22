package com.flyflow.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flyflow.biz.entity.Role;
import com.flyflow.common.dto.R;
import com.flyflow.common.dto.third.RoleDto;

import java.util.List;
import java.util.Set;

/**
 * 角色 服务类
 *
 * @author Vincent
 * @since 2023-06-08
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据用户id获取角色key集合
     *
     * @param userId
     * @return
     */
    R<Set<String>> queryRoleKeyByUserId(String userId);

    /**
     * 创建角色
     *
     * @param roleDto
     * @return
     */
    R create(RoleDto roleDto);

    /**
     * 修改角色
     *
     * @param roleDto
     * @return
     */
    R edit(RoleDto roleDto);

    /**
     * 删除角色
     *
     * @param roleDto
     * @return
     */
    R delete(RoleDto roleDto);

    /**
     * 获取角色的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 菜单ID集合(包括按钮权限ID)
     */
    R<List<Long>> getRoleMenuIds(String roleId);

    /**
     * 修改角色的资源权限
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    R updateRoleMenus(String roleId, List<Long> menuIds);
}
package com.spyker.flowable.biz.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;

import com.spyker.flowable.biz.entity.RoleMenu;
import com.spyker.flowable.biz.mapper.RoleMenuMapper;
import com.spyker.flowable.biz.service.IRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表 服务实现类
 *
 * @author Vincent
 * @since 2023-06-10
 */
@Service
public class RoleMenuServiceImpl extends MPJBaseServiceImpl<RoleMenuMapper, RoleMenu>
        implements IRoleMenuService {}
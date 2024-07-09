package cc.flyflow.biz.service.impl;

import cc.flyflow.biz.entity.RoleMenu;
import cc.flyflow.biz.mapper.RoleMenuMapper;
import cc.flyflow.biz.service.IRoleMenuService;

import com.github.yulichang.base.MPJBaseServiceImpl;

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
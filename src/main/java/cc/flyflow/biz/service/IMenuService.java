package cc.flyflow.biz.service;

import cc.flyflow.biz.entity.Menu;
import cc.flyflow.biz.vo.MenuVO;
import cc.flyflow.common.dto.R;
import com.github.yulichang.base.MPJBaseService;

import java.util.Set;

/**
 * 菜单管理 服务类
 *
 * @author Vincent
 * @since 2023-06-10
 */
public interface IMenuService extends MPJBaseService<Menu> {

    /**
     * 路由列表
     *
     * @return
     */
    R routes();

    /**
     * 根据角色id集合查询权限集合
     *
     * @param roleKeySet
     * @return
     */
    R<Set<String>> listRolePerms(Set<String> roleKeySet);

    /**
     * 树形显示菜单
     *
     * @return
     */
    R treeAll(String keywords, Integer status);

    /**
     * 创建菜单
     *
     * @param menuVO
     * @return
     */
    R create(MenuVO menuVO);

    /**
     * 编辑菜单
     *
     * @param menuVO
     * @return
     */
    R edit(MenuVO menuVO);

    /**
     * 删除菜单
     *
     * @param menuVO
     * @return
     */
    R delete(MenuVO menuVO);
}
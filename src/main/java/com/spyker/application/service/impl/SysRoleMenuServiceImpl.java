package com.spyker.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.application.entity.SysRoleMenu;
import com.spyker.application.mapper.SysRoleMenuMapper;
import com.spyker.application.search.SysRoleMenuSearch;
import com.spyker.application.service.SysRoleMenuService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRoleMenu> query(SysRoleMenuSearch search) {
        List<SysRoleMenu> SysRoleMenuList = sysRoleMenuMapper.query(search);

        return SysRoleMenuList;
    }

    @Override
    public IPage<SysRoleMenu> queryPage(IPage<SysRoleMenu> page, SysRoleMenuSearch search) {
        page = sysRoleMenuMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysRoleMenu get(String id) {
        SysRoleMenu SysRoleMenu = getById(id);

        return SysRoleMenu;
    }

    @Override
    public RestResponse<?> insert(SysRoleMenu SysRoleMenu) {
        save(SysRoleMenu);

        return RestResponse.success(SysRoleMenu);
    }

    @Override
    public RestResponse<?> update(SysRoleMenu SysRoleMenu) {
        updateById(SysRoleMenu);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}
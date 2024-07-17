package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysMenu;
import com.spyker.commons.mapper.SysMenuMapper;
import com.spyker.commons.search.SysMenuSearch;
import com.spyker.commons.service.SysMenuService;
import com.spyker.framework.web.response.RestResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单权限表 服务实现类
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> query(SysMenuSearch search) {
        List<SysMenu> sysMenuList = sysMenuMapper.query(search);

        return sysMenuList;
    }

    @Override
    public IPage<SysMenu> queryPage(IPage<SysMenu> page, SysMenuSearch search) {
        page = sysMenuMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysMenu get(String id) {
        SysMenu sysMenu = getById(id);

        return sysMenu;
    }

    @Override
    public RestResponse<?> insert(SysMenu sysMenu) {
        save(sysMenu);

        return RestResponse.success(sysMenu);
    }

    @Override
    public RestResponse<?> update(SysMenu sysMenu) {
        updateById(sysMenu);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }
}
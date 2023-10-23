package com.spyker.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.base.entity.SysMenu;
import com.spyker.base.mapper.SysMenuMapper;
import com.spyker.base.search.SysMenuSearch;
import com.spyker.base.service.SysMenuService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> query(SysMenuSearch search) {
        List<SysMenu> SysMenuList = sysMenuMapper.query(search);

        return SysMenuList;
    }

    @Override
    public IPage<SysMenu> queryPage(IPage<SysMenu> page, SysMenuSearch search) {
        page = sysMenuMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysMenu get(String id) {
        SysMenu SysMenu = getById(id);

        return SysMenu;
    }

    @Override
    public RestResponse<?> insert(SysMenu SysMenu) {
        save(SysMenu);

        return RestResponse.success(SysMenu);
    }

    @Override
    public RestResponse<?> update(SysMenu SysMenu) {
        updateById(SysMenu);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}
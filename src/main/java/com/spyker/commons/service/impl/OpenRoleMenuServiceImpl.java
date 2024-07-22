package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.OpenRoleMenu;
import com.spyker.commons.mapper.OpenRoleMenuMapper;
import com.spyker.commons.search.OpenRoleMenuSearch;
import com.spyker.commons.service.OpenRoleMenuService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色和菜单关联表 服务实现类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "OpenRoleMenu")
public class OpenRoleMenuServiceImpl extends ServiceImpl<OpenRoleMenuMapper, OpenRoleMenu>
        implements OpenRoleMenuService {

    // @formatter:off

    private final OpenRoleMenuMapper openRoleMenuMapper;

    @Override
    public List<OpenRoleMenu> query(OpenRoleMenuSearch search) {

        List<OpenRoleMenu> result = openRoleMenuMapper.query(search);
        log.info("result------>{}", result);

        return result;
    }

    @Override
    public IPage<OpenRoleMenu> queryPage(IPage<OpenRoleMenu> page, OpenRoleMenuSearch search) {

        page = openRoleMenuMapper.queryPage(page, search);
        log.info("page------>{}", page);

        return page;
    }

    /**
     * @param id
     * @return @Cacheable @Cacheble注解表示这个方法有了缓存的功能，方法的返回值会被缓存下来，下一次调用该方法前， 会去检查是否缓存中已经有值
     *     ，如果有就直接返回，不调用方法。如果没有，就调用方法，然后把结果缓存起来。这个注解一般用在查询方法上。
     */
    @Override
    // @Cacheable(key = "#id")
    public OpenRoleMenu get(String id) {
        OpenRoleMenu result = getById(id);

        log.info("result------>{}", result);

        return result;
    }

    /**
     * @param openRoleMenu
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#openRoleMenu.id")
    public OpenRoleMenu insert(OpenRoleMenu openRoleMenu) {
        save(openRoleMenu);
        return openRoleMenu;
    }

    /**
     * @param openRoleMenu
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#openRoleMenu.id")
    public OpenRoleMenu update(OpenRoleMenu openRoleMenu) {
        updateById(openRoleMenu);
        return openRoleMenu;
    }

    /**
     * @param id
     * @return @CacheEvict 使用了CacheEvict注解的方法，会清空指定缓存。 一般用在更新或者删除的方法上。
     */
    @Override
    // @CacheEvict(key = "#id")
    public boolean delete(String id) {
        return removeById(id);
    }
}
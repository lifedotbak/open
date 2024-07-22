package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.OpenMenu;
import com.spyker.commons.mapper.OpenMenuMapper;
import com.spyker.commons.search.OpenMenuSearch;
import com.spyker.commons.service.OpenMenuService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单管理 服务实现类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "OpenMenu")
public class OpenMenuServiceImpl extends ServiceImpl<OpenMenuMapper, OpenMenu>
        implements OpenMenuService {

    // @formatter:off

    private final OpenMenuMapper openMenuMapper;

    @Override
    public List<OpenMenu> query(OpenMenuSearch search) {

        List<OpenMenu> result = openMenuMapper.query(search);
        log.info("result------>{}", result);

        return result;
    }

    @Override
    public IPage<OpenMenu> queryPage(IPage<OpenMenu> page, OpenMenuSearch search) {

        page = openMenuMapper.queryPage(page, search);
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
    public OpenMenu get(String id) {
        OpenMenu result = getById(id);

        log.info("result------>{}", result);

        return result;
    }

    /**
     * @param openMenu
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#openMenu.id")
    public OpenMenu insert(OpenMenu openMenu) {
        save(openMenu);
        return openMenu;
    }

    /**
     * @param openMenu
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#openMenu.id")
    public OpenMenu update(OpenMenu openMenu) {
        updateById(openMenu);
        return openMenu;
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
package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysProcessNodeData;
import com.spyker.commons.mapper.SysProcessNodeDataMapper;
import com.spyker.commons.search.SysProcessNodeDataSearch;
import com.spyker.commons.service.SysProcessNodeDataService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 流程节点数据 服务实现类 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "SysProcessNodeData")
public class SysProcessNodeDataServiceImpl
        extends ServiceImpl<SysProcessNodeDataMapper, SysProcessNodeData>
        implements SysProcessNodeDataService {

    private final SysProcessNodeDataMapper sysProcessNodeDataMapper;

    /**
     * @param id
     * @return @CacheEvict 使用了CacheEvict注解的方法，会清空指定缓存。 一般用在更新或者删除的方法上。
     */
    @Override
    // @CacheEvict(key = "#id")
    public boolean delete(String id) {
        return removeById(id);
    }

    /**
     * @param id
     * @return @Cacheable @Cacheble注解表示这个方法有了缓存的功能，方法的返回值会被缓存下来，下一次调用该方法前， 会去检查是否缓存中已经有值
     *     ，如果有就直接返回，不调用方法。如果没有，就调用方法，然后把结果缓存起来。这个注解一般用在查询方法上。
     */
    @Override
    // @Cacheable(key = "#id")
    public SysProcessNodeData get(String id) {
        SysProcessNodeData result = getById(id);

        log.info("result------>{}", result);

        return result;
    }

    /**
     * @param sysProcessNodeData
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#sysProcessNodeData.id")
    public SysProcessNodeData insert(SysProcessNodeData sysProcessNodeData) {
        save(sysProcessNodeData);
        return sysProcessNodeData;
    }

    @Override
    public List<SysProcessNodeData> query(SysProcessNodeDataSearch search) {

        List<SysProcessNodeData> result = sysProcessNodeDataMapper.query(search);
        log.info("result------>{}", result);

        return result;
    }

    @Override
    public IPage<SysProcessNodeData> queryPage(
            IPage<SysProcessNodeData> page, SysProcessNodeDataSearch search) {

        page = sysProcessNodeDataMapper.queryPage(page, search);
        log.info("page------>{}", page);

        return page;
    }

    /**
     * @param sysProcessNodeData
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#sysProcessNodeData.id")
    public SysProcessNodeData update(SysProcessNodeData sysProcessNodeData) {
        updateById(sysProcessNodeData);
        return sysProcessNodeData;
    }
}

package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.OpenProcessInstanceAssignUserRecord;
import com.spyker.commons.mapper.OpenProcessInstanceAssignUserRecordMapper;
import com.spyker.commons.search.OpenProcessInstanceAssignUserRecordSearch;
import com.spyker.commons.service.OpenProcessInstanceAssignUserRecordService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流程节点记录-执行人 服务实现类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "OpenProcessInstanceAssignUserRecord")
public class OpenProcessInstanceAssignUserRecordServiceImpl
        extends ServiceImpl<
                OpenProcessInstanceAssignUserRecordMapper, OpenProcessInstanceAssignUserRecord>
        implements OpenProcessInstanceAssignUserRecordService {

    // @formatter:off

    private final OpenProcessInstanceAssignUserRecordMapper
            openProcessInstanceAssignUserRecordMapper;

    @Override
    public List<OpenProcessInstanceAssignUserRecord> query(
            OpenProcessInstanceAssignUserRecordSearch search) {

        List<OpenProcessInstanceAssignUserRecord> result =
                openProcessInstanceAssignUserRecordMapper.query(search);
        log.info("result------>{}", result);

        return result;
    }

    @Override
    public IPage<OpenProcessInstanceAssignUserRecord> queryPage(
            IPage<OpenProcessInstanceAssignUserRecord> page,
            OpenProcessInstanceAssignUserRecordSearch search) {

        page = openProcessInstanceAssignUserRecordMapper.queryPage(page, search);
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
    public OpenProcessInstanceAssignUserRecord get(String id) {
        OpenProcessInstanceAssignUserRecord result = getById(id);

        log.info("result------>{}", result);

        return result;
    }

    /**
     * @param openProcessInstanceAssignUserRecord
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#openProcessInstanceAssignUserRecord.id")
    public OpenProcessInstanceAssignUserRecord insert(
            OpenProcessInstanceAssignUserRecord openProcessInstanceAssignUserRecord) {
        save(openProcessInstanceAssignUserRecord);
        return openProcessInstanceAssignUserRecord;
    }

    /**
     * @param openProcessInstanceAssignUserRecord
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#openProcessInstanceAssignUserRecord.id")
    public OpenProcessInstanceAssignUserRecord update(
            OpenProcessInstanceAssignUserRecord openProcessInstanceAssignUserRecord) {
        updateById(openProcessInstanceAssignUserRecord);
        return openProcessInstanceAssignUserRecord;
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
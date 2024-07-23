package com.spyker.commons.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.commons.entity.SysProcessInstanceAssignUserRecord;
import com.spyker.commons.mapper.SysProcessInstanceAssignUserRecordMapper;
import com.spyker.commons.search.SysProcessInstanceAssignUserRecordSearch;
import com.spyker.commons.service.SysProcessInstanceAssignUserRecordService;

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
 * @since 2024-07-23
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "SysProcessInstanceAssignUserRecord")
public class SysProcessInstanceAssignUserRecordServiceImpl
        extends ServiceImpl<
                SysProcessInstanceAssignUserRecordMapper, SysProcessInstanceAssignUserRecord>
        implements SysProcessInstanceAssignUserRecordService {

    private final SysProcessInstanceAssignUserRecordMapper sysProcessInstanceAssignUserRecordMapper;

    @Override
    public List<SysProcessInstanceAssignUserRecord> query(
            SysProcessInstanceAssignUserRecordSearch search) {

        List<SysProcessInstanceAssignUserRecord> result =
                sysProcessInstanceAssignUserRecordMapper.query(search);
        log.info("result------>{}", result);

        return result;
    }

    @Override
    public IPage<SysProcessInstanceAssignUserRecord> queryPage(
            IPage<SysProcessInstanceAssignUserRecord> page,
            SysProcessInstanceAssignUserRecordSearch search) {

        page = sysProcessInstanceAssignUserRecordMapper.queryPage(page, search);
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
    public SysProcessInstanceAssignUserRecord get(String id) {
        SysProcessInstanceAssignUserRecord result = getById(id);

        log.info("result------>{}", result);

        return result;
    }

    /**
     * @param sysProcessInstanceAssignUserRecord
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#sysProcessInstanceAssignUserRecord.id")
    public SysProcessInstanceAssignUserRecord insert(
            SysProcessInstanceAssignUserRecord sysProcessInstanceAssignUserRecord) {
        save(sysProcessInstanceAssignUserRecord);
        return sysProcessInstanceAssignUserRecord;
    }

    /**
     * @param sysProcessInstanceAssignUserRecord
     * @return @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来， 供其它地方使用。它通常用在新增方法上。
     */
    @Override
    // @CachePut(key = "#sysProcessInstanceAssignUserRecord.id")
    public SysProcessInstanceAssignUserRecord update(
            SysProcessInstanceAssignUserRecord sysProcessInstanceAssignUserRecord) {
        updateById(sysProcessInstanceAssignUserRecord);
        return sysProcessInstanceAssignUserRecord;
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
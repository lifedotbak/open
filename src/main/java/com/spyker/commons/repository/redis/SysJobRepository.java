package com.spyker.commons.repository.redis;

import com.spyker.commons.entity.SysJob;

import org.springframework.data.repository.CrudRepository;

/**
 * 定时任务调度表
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
/** 一个负责存储和检索的组件，定义一个repository接口,和mapper功能类似，但不能放到mapper包下面，因为mybatis的扫描，放同一包下面会提示类冲突 */
public interface SysJobRepository extends CrudRepository<SysJob, String> {}
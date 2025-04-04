package com.spyker.commons.repository.redis;

import com.spyker.commons.entity.SysProcessInstanceUserCopy;

import org.springframework.data.repository.CrudRepository;

/**
 * 流程抄送数据--用户和实例唯一值
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
/** 一个负责存储和检索的组件，定义一个repository接口,和mapper功能类似，但不能放到mapper包下面，因为mybatis的扫描，放同一包下面会提示类冲突 */
public interface SysProcessInstanceUserCopyRepository
        extends CrudRepository<SysProcessInstanceUserCopy, String> {}

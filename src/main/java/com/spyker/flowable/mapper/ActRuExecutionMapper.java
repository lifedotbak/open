package com.spyker.flowable.mapper;

import com.spyker.flowable.entity.ActRuExecution;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 执行实例Mapper接口
 *
 * @date 2022-07-13
 */
public interface ActRuExecutionMapper {
    /**
     * 查询执行实例
     *
     * @param id 执行实例主键
     * @return 执行实例
     */
    ActRuExecution selectActRuExecutionById(String id);

    /**
     * 查询执行实例列表
     *
     * @param actRuExecution 执行实例
     * @return 执行实例集合
     */
    List<ActRuExecution> selectActRuExecutionList(ActRuExecution actRuExecution);

    /**
     * 用流程名称查询实例
     *
     * @return
     */
    List<ActRuExecution> selectActRuExecutionListByProcessName(@Param("name") String name);

    /**
     * 新增执行实例
     *
     * @param actRuExecution 执行实例
     * @return 结果
     */
    int insertActRuExecution(ActRuExecution actRuExecution);

    /**
     * 修改执行实例
     *
     * @param actRuExecution 执行实例
     * @return 结果
     */
    int updateActRuExecution(ActRuExecution actRuExecution);

    /**
     * 删除执行实例
     *
     * @param id 执行实例主键
     * @return 结果
     */
    int deleteActRuExecutionById(String id);

    /**
     * 批量删除执行实例
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteActRuExecutionByIds(String[] ids);
}
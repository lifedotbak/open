package com.spyker.commons.mapper;

import com.spyker.commons.entity.SysProcessInstanceExecution;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.SysProcessInstanceExecutionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 流程执行id数据 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessInstanceExecutionMapperTest extends BaseTest {

    @Autowired private SysProcessInstanceExecutionMapper sysProcessInstanceExecutionMapper;
}
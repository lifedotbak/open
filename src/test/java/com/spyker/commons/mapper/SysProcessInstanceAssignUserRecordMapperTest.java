package com.spyker.commons.mapper;

import com.spyker.commons.entity.SysProcessInstanceAssignUserRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.SysProcessInstanceAssignUserRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 流程节点记录-执行人 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessInstanceAssignUserRecordMapperTest extends BaseTest {

    @Autowired
    private SysProcessInstanceAssignUserRecordMapper sysProcessInstanceAssignUserRecordMapper;
}
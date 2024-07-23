package com.spyker.commons.mapper;

import com.spyker.commons.entity.SysProcessStarter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.SysProcessStarterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 流程发起人范围 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessStarterMapperTest extends BaseTest {

    @Autowired private SysProcessStarterMapper sysProcessStarterMapper;
}
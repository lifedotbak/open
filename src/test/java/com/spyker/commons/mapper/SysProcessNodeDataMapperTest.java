package com.spyker.commons.mapper;

import com.spyker.commons.entity.SysProcessNodeData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.SysProcessNodeDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 流程节点数据 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessNodeDataMapperTest extends BaseTest {

    @Autowired private SysProcessNodeDataMapper sysProcessNodeDataMapper;
}
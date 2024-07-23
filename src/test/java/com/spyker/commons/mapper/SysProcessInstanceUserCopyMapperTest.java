package com.spyker.commons.mapper;

import com.spyker.commons.entity.SysProcessInstanceUserCopy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.SysProcessInstanceUserCopyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 流程抄送数据--用户和实例唯一值 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Slf4j
public class SysProcessInstanceUserCopyMapperTest extends BaseTest {

    @Autowired private SysProcessInstanceUserCopyMapper sysProcessInstanceUserCopyMapper;
}
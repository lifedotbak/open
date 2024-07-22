package com.spyker.commons.mapper;

import com.spyker.commons.entity.OpenProcessInstanceUserCopy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.OpenProcessInstanceUserCopyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 流程抄送数据--用户和实例唯一值 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenProcessInstanceUserCopyMapperTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessInstanceUserCopyMapper openProcessInstanceUserCopyMapper;
}
package com.spyker.commons.mapper;

import com.spyker.commons.entity.OpenProcessInstanceCopy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.OpenProcessInstanceCopyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 流程抄送数据 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class OpenProcessInstanceCopyMapperTest extends BaseTest {

    // @formatter:off

    @Autowired private OpenProcessInstanceCopyMapper openProcessInstanceCopyMapper;
}
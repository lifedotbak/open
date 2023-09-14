package com.spyker.application.mapper;

import com.spyker.application.entity.TestCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.application.mapper.TestCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.application.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-14
 */
@Slf4j
public class TestCodeMapperTest extends BaseTest {

    @Autowired
    private TestCodeMapper testCodeMapper;

}

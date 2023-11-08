package com.spyker.commons.mapper;

import com.spyker.commons.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.SysDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-11-07
 */
@Slf4j
public class SysDeptMapperTest extends BaseTest {

    @Autowired
    private SysDeptMapper sysDeptMapper;

}
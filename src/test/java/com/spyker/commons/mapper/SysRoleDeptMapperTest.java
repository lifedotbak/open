package com.spyker.commons.mapper;

import com.spyker.commons.entity.SysRoleDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spyker.commons.mapper.SysRoleDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 角色和部门关联表 Mapper 接口测试类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Slf4j
public class SysRoleDeptMapperTest extends BaseTest {

    // @formatter:off

    @Autowired private SysRoleDeptMapper sysRoleDeptMapper;
}
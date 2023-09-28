package com.spyker.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.application.entity.SysRoleDept;
import com.spyker.application.mapper.SysRoleDeptMapper;
import com.spyker.application.search.SysRoleDeptSearch;
import com.spyker.application.service.SysRoleDeptService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色和部门关联表 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDept> implements SysRoleDeptService {

    private final SysRoleDeptMapper sysRoleDeptMapper;

    @Override
    public List<SysRoleDept> query(SysRoleDeptSearch search) {
        List<SysRoleDept> SysRoleDeptList = sysRoleDeptMapper.query(search);

        return SysRoleDeptList;
    }

    @Override
    public IPage<SysRoleDept> queryPage(IPage<SysRoleDept> page, SysRoleDeptSearch search) {
        page = sysRoleDeptMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysRoleDept get(String id) {
        SysRoleDept SysRoleDept = getById(id);

        return SysRoleDept;
    }

    @Override
    public RestResponse<?> insert(SysRoleDept SysRoleDept) {
        save(SysRoleDept);

        return RestResponse.success(SysRoleDept);
    }

    @Override
    public RestResponse<?> update(SysRoleDept SysRoleDept) {
        updateById(SysRoleDept);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}
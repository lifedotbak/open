package com.spyker.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.application.entity.SysUserPost;
import com.spyker.application.mapper.SysUserPostMapper;
import com.spyker.application.search.SysUserPostSearch;
import com.spyker.application.service.SysUserPostService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户与岗位关联表 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost> implements SysUserPostService {

    private final SysUserPostMapper sysUserPostMapper;

    @Override
    public List<SysUserPost> query(SysUserPostSearch search) {
        List<SysUserPost> SysUserPostList = sysUserPostMapper.query(search);

        return SysUserPostList;
    }

    @Override
    public IPage<SysUserPost> queryPage(IPage<SysUserPost> page, SysUserPostSearch search) {
        page = sysUserPostMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysUserPost get(String id) {
        SysUserPost SysUserPost = getById(id);

        return SysUserPost;
    }

    @Override
    public RestResponse<?> insert(SysUserPost SysUserPost) {
        save(SysUserPost);

        return RestResponse.success(SysUserPost);
    }

    @Override
    public RestResponse<?> update(SysUserPost SysUserPost) {
        updateById(SysUserPost);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}
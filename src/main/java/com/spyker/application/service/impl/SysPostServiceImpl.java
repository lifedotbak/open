package com.spyker.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spyker.application.entity.SysPost;
import com.spyker.application.mapper.SysPostMapper;
import com.spyker.application.search.SysPostSearch;
import com.spyker.application.service.SysPostService;
import com.spyker.framework.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 岗位信息表 服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {

    private final SysPostMapper sysPostMapper;

    @Override
    public List<SysPost> query(SysPostSearch search) {
        List<SysPost> SysPostList = sysPostMapper.query(search);

        return SysPostList;
    }

    @Override
    public IPage<SysPost> queryPage(IPage<SysPost> page, SysPostSearch search) {
        page = sysPostMapper.queryPage(page, search);

        return page;
    }

    @Override
    public SysPost get(String id) {
        SysPost SysPost = getById(id);

        return SysPost;
    }

    @Override
    public RestResponse<?> insert(SysPost SysPost) {
        save(SysPost);

        return RestResponse.success(SysPost);
    }

    @Override
    public RestResponse<?> update(SysPost SysPost) {
        updateById(SysPost);

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id) {
        removeById(id);

        return RestResponse.success();
    }

}
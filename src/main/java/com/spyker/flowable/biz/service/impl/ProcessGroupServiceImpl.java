package com.spyker.flowable.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.spyker.flowable.biz.entity.ProcessGroup;
import com.spyker.flowable.biz.mapper.ProcessGroupMapper;
import com.spyker.flowable.biz.service.IProcessGroupService;
import com.spyker.flowable.common.dto.R;
import com.spyker.flowable.common.utils.TenantUtil;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.PostConstruct;

/**
 * 服务实现类
 *
 * @author Vincent
 * @since 2023-05-25
 */
@Service
public class ProcessGroupServiceImpl extends ServiceImpl<ProcessGroupMapper, ProcessGroup>
        implements IProcessGroupService {

    @PostConstruct
    public void init() {
        //        int index=0;
        //        List<ProcessGroup> processGroupList = this.lambdaQuery()
        //                .eq(ProcessGroup::getTenantId,TenantUtil.get())
        //                .orderByAsc(ProcessGroup::getSort).list();
        //        for (ProcessGroup processGroup : processGroupList) {
        //
        //            this.lambdaUpdate().set(ProcessGroup::getSort,index)
        //                    .eq(ProcessGroup::getId,processGroup.getId())
        //                    .update();
        //
        //            index++;
        //        }
    }

    /**
     * 组列表
     *
     * @return
     */
    @Override
    public R<List<ProcessGroup>> queryList() {
        List<ProcessGroup> processGroupList =
                this.lambdaQuery()
                        .eq(ProcessGroup::getTenantId, TenantUtil.get())
                        .orderByDesc(ProcessGroup::getSort)
                        .list();

        return R.success(processGroupList);
    }

    /**
     * 新增表单分组
     *
     * @param processGroup 分组名
     * @return 添加结果
     */
    @Override
    public R create(ProcessGroup processGroup) {
        List<ProcessGroup> list = this.lambdaQuery().orderByDesc(ProcessGroup::getSort).list();
        ProcessGroup pg = new ProcessGroup();
        pg.setSort(list.isEmpty() ? 0 : list.get(0).getSort() + 1);
        pg.setGroupName(processGroup.getGroupName());
        pg.setTenantId(TenantUtil.get());

        this.save(pg);
        return R.success();
    }

    /**
     * 上移
     *
     * @param processGroup
     * @return
     */
    @Override
    public R topSort(ProcessGroup processGroup) {
        ProcessGroup pg = this.getById(processGroup.getId());
        Integer sort = pg.getSort();

        List<ProcessGroup> list =
                this.lambdaQuery()
                        .gt(ProcessGroup::getSort, sort)
                        .eq(ProcessGroup::getTenantId, TenantUtil.get())
                        .orderByAsc(ProcessGroup::getSort)
                        .list();
        ProcessGroup pg1 = list.get(0);
        Integer sort1 = pg1.getSort();

        pg.setSort(sort1);
        this.updateById(pg);

        pg1.setSort(sort);
        this.updateById(pg1);

        return R.success();
    }

    /**
     * 下移
     *
     * @param processGroup
     * @return
     */
    @Override
    public R bottomSort(ProcessGroup processGroup) {
        ProcessGroup pg = this.getById(processGroup.getId());
        Integer sort = pg.getSort();

        List<ProcessGroup> list =
                this.lambdaQuery()
                        .lt(ProcessGroup::getSort, sort)
                        .eq(ProcessGroup::getTenantId, TenantUtil.get())
                        .orderByDesc(ProcessGroup::getSort)
                        .list();
        ProcessGroup pg1 = list.get(0);
        Integer sort1 = pg1.getSort();

        pg.setSort(sort1);
        this.updateById(pg);

        pg1.setSort(sort);
        this.updateById(pg1);
        return R.success();
    }

    /**
     * 修改组
     *
     * @param processGroup
     * @return
     */
    @Override
    public R edit(ProcessGroup processGroup) {
        ProcessGroup pg = this.getById(processGroup.getId());

        pg.setGroupName(processGroup.getGroupName());

        BeanUtil.copyProperties(processGroup, pg, CopyOptions.create().setIgnoreNullValue(true));

        this.updateById(pg);
        return R.success();
    }

    /**
     * 删除分组
     *
     * @param id
     * @return
     */
    @Override
    public R delete(long id) {
        this.removeById(id);
        return R.success();
    }
}
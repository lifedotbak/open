package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysProcessForm;
import com.spyker.commons.search.SysProcessFormSearch;

import java.util.List;

/**
 * 流程表单 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
public interface SysProcessFormService extends IService<SysProcessForm> {

    List<SysProcessForm> query(SysProcessFormSearch search);

    IPage<SysProcessForm> queryPage(IPage<SysProcessForm> page, SysProcessFormSearch search);

    SysProcessForm get(String id);

    SysProcessForm insert(SysProcessForm sysProcessForm);

    SysProcessForm update(SysProcessForm sysProcessForm);

    boolean delete(String id);
}
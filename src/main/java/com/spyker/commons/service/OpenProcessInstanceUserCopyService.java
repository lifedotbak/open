package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.OpenProcessInstanceUserCopy;
import com.spyker.commons.search.OpenProcessInstanceUserCopySearch;

import java.util.List;

/**
 * 流程抄送数据--用户和实例唯一值 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenProcessInstanceUserCopyService extends IService<OpenProcessInstanceUserCopy> {

    // @formatter:off

    List<OpenProcessInstanceUserCopy> query(OpenProcessInstanceUserCopySearch search);

    IPage<OpenProcessInstanceUserCopy> queryPage(
            IPage<OpenProcessInstanceUserCopy> page, OpenProcessInstanceUserCopySearch search);

    OpenProcessInstanceUserCopy get(String id);

    OpenProcessInstanceUserCopy insert(OpenProcessInstanceUserCopy openProcessInstanceUserCopy);

    OpenProcessInstanceUserCopy update(OpenProcessInstanceUserCopy openProcessInstanceUserCopy);

    boolean delete(String id);
}
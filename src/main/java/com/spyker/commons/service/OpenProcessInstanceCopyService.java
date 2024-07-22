package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.OpenProcessInstanceCopy;
import com.spyker.commons.search.OpenProcessInstanceCopySearch;

import java.util.List;

/**
 * 流程抄送数据 服务接口
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
public interface OpenProcessInstanceCopyService extends IService<OpenProcessInstanceCopy> {

    // @formatter:off

    List<OpenProcessInstanceCopy> query(OpenProcessInstanceCopySearch search);

    IPage<OpenProcessInstanceCopy> queryPage(
            IPage<OpenProcessInstanceCopy> page, OpenProcessInstanceCopySearch search);

    OpenProcessInstanceCopy get(String id);

    OpenProcessInstanceCopy insert(OpenProcessInstanceCopy openProcessInstanceCopy);

    OpenProcessInstanceCopy update(OpenProcessInstanceCopy openProcessInstanceCopy);

    boolean delete(String id);
}
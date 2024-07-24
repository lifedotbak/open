package com.spyker.flowable.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.flowable.biz.entity.ProcessInstanceUserCopy;
import com.spyker.flowable.biz.vo.ProcessDataQueryVO;
import com.spyker.flowable.common.dto.R;

public interface IProcessInstanceUserCopyService extends IService<ProcessInstanceUserCopy> {

    /**
     * 查询抄送给我的(根据实例id去重)
     *
     * @param pageDto
     * @return
     */
    R queryMineCCProcessInstance(ProcessDataQueryVO pageDto);
}
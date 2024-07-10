package com.flyflow.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flyflow.biz.entity.ProcessInstanceUserCopy;
import com.flyflow.biz.vo.ProcessDataQueryVO;
import com.flyflow.common.dto.R;

public interface IProcessInstanceUserCopyService extends IService<ProcessInstanceUserCopy> {

    /**
     * 查询抄送给我的(根据实例id去重)
     *
     * @param pageDto
     * @return
     */
    R queryMineCCProcessInstance(ProcessDataQueryVO pageDto);
}
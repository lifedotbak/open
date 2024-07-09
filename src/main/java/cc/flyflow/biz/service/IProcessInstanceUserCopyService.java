package cc.flyflow.biz.service;

import cc.flyflow.biz.entity.ProcessInstanceUserCopy;
import cc.flyflow.biz.vo.ProcessDataQueryVO;
import cc.flyflow.common.dto.R;

import com.baomidou.mybatisplus.extension.service.IService;

public interface IProcessInstanceUserCopyService extends IService<ProcessInstanceUserCopy> {

    /**
     * 查询抄送给我的(根据实例id去重)
     *
     * @param pageDto
     * @return
     */
    R queryMineCCProcessInstance(ProcessDataQueryVO pageDto);
}
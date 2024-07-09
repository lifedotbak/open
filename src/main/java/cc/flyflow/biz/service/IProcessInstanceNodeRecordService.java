package cc.flyflow.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cc.flyflow.biz.entity.ProcessInstanceNodeRecord;
import cc.flyflow.common.dto.ProcessInstanceNodeRecordParamDto;
import cc.flyflow.common.dto.R;

/**
 * 流程节点记录 服务类
 *
 * @author xiaoge
 * @since 2023-05-10
 */
public interface IProcessInstanceNodeRecordService extends IService<ProcessInstanceNodeRecord> {
    /**
     * 节点开始
     *
     * @param processInstanceNodeRecordParamDto
     * @return
     */
    R startNodeEvent(ProcessInstanceNodeRecordParamDto processInstanceNodeRecordParamDto);

    /**
     * 节点结束
     *
     * @param processInstanceNodeRecordParamDto
     * @return
     */
    R endNodeEvent(ProcessInstanceNodeRecordParamDto processInstanceNodeRecordParamDto);

    /**
     * 节点取消
     *
     * @param processInstanceNodeRecordParamDto
     * @return
     */
    R cancelNodeEvent(ProcessInstanceNodeRecordParamDto processInstanceNodeRecordParamDto);
}
package cc.flyflow.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cc.flyflow.biz.entity.Process;
import cc.flyflow.biz.vo.ProcessVO;
import cc.flyflow.common.dto.R;

import java.util.List;

/**
 * 服务类
 *
 * @author xiaoge
 * @since 2023-05-25
 */
public interface IProcessService extends IService<Process> {

    /**
     * 获取详细数据
     *
     * @param flowId
     * @return
     */
    R<ProcessVO> getDetail(String flowId);

    /**
     * 根据流程唯一标识查询流程列表
     *
     * @param uniqueId
     * @return
     */
    R<ProcessVO> getListByUniqueId(String uniqueId);

    /**
     * 设置主流程
     *
     * @param flowId
     * @return
     */
    R setMainProcess(String flowId);

    Process getByFlowId(String flowId);

    /**
     * 查询流程数据 包括已经被删除的 慎用
     *
     * @param flowId
     * @return
     */
    Process getByFlowIdContainDeleted(String flowId);

    Process getByUniqueId(String uniqueId);

    void updateByFlowId(Process process);

    void stop(String flowId);

    /**
     * 创建流程
     *
     * @param processVO
     * @return
     */
    R create(ProcessVO processVO);

    /**
     * 编辑表单
     *
     * @param flowId 摸板ID
     * @param type 类型 stop using delete
     * @return 操作结果
     */
    R update(String flowId, String type, Long groupId);

    /**
     * 查询所有关联的流程id
     *
     * @param flowIdList
     * @return
     */
    R<List<String>> getAllRelatedFlowId(List<String> flowIdList);
}
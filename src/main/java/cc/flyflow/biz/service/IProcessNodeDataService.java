package cc.flyflow.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cc.flyflow.biz.entity.ProcessNodeData;
import cc.flyflow.common.dto.ProcessNodeDataDto;
import cc.flyflow.common.dto.R;
import cc.flyflow.common.dto.flow.Node;

/**
 * 流程节点数据 服务类
 *
 * @author Vincent
 * @since 2023-05-07
 */
public interface IProcessNodeDataService extends IService<ProcessNodeData> {

    /**
     * 保存流程节点数据
     *
     * @param processNodeDataDto
     * @return
     */
    R saveNodeData(ProcessNodeDataDto processNodeDataDto);

    /***
     * 获取节点数据
     * @param flowId
     * @param nodeId
     * @return
     */
    R<String> getNodeData(String flowId, String nodeId);

    R<Node> getNode(String flowId, String nodeId);
}
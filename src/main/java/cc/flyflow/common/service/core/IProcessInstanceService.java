package cc.flyflow.common.service.core;

import cc.flyflow.common.dto.IndexPageStatistics;
import cc.flyflow.common.dto.ProcessInstanceParamDto;
import cc.flyflow.common.dto.R;
import cc.flyflow.common.dto.VariableQueryParamDto;

import java.util.Map;

/**
 * @author Huijun Zhao
 * @description
 * @date 2023-08-04 16:40
 */
public interface IProcessInstanceService {

    /**
     * 删除流程
     *
     * @param processInstanceParamDto
     * @return
     */
    R delete(ProcessInstanceParamDto processInstanceParamDto);

    /**
     * 查询统计数据
     *
     * @param userId
     * @return
     */
    R<IndexPageStatistics> querySimpleData(String userId);

    /**
     * 查询变量
     *
     * @param paramDto
     * @return
     */
    R<Map<String, Object>> queryVariables(VariableQueryParamDto paramDto);
}
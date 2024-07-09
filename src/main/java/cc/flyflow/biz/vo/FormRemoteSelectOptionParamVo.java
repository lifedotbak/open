package cc.flyflow.biz.vo;

import cc.flyflow.common.dto.flow.HttpSetting;
import lombok.Data;

import java.util.Map;

/** 表单远程的下拉选项 */
@Data
public class FormRemoteSelectOptionParamVo {
    /** 流程id */
    private String flowId;

    private String processInstanceId;

    private HttpSetting httpSetting;

    private Map<String, Object> paramMap;
}
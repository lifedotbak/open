package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流程表单查询类
 *
 * @author 121232224@qq.com
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenProcessFormSearch对象", description = "流程表单Search对象")
public class OpenProcessFormSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "流程唯一id")
    private String uniqueId;

    @Schema(description = "表单名称")
    private String formName;

    @Schema(description = "表单id")
    private String formId;

    @Schema(description = "表单类型")
    private String formType;

    @Schema(description = "表单属性")
    private String props;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "流程id")
    private String flowId;
}

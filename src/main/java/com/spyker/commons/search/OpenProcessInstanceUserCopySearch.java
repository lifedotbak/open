package com.spyker.commons.search;

// @formatter:off

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流程抄送数据--用户和实例唯一值查询类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenProcessInstanceUserCopySearch对象", description = "流程抄送数据--用户和实例唯一值Search对象")
public class OpenProcessInstanceUserCopySearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "发起人")
    private String startUserId;

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "实例id")
    private String processInstanceId;

    @Schema(description = "分组id")
    private Long groupId;

    @Schema(description = "分组名称")
    private String groupName;

    @Schema(description = "流程名称")
    private String processName;

    @Schema(description = "抄送人id")
    private String userId;

    @Schema(description = "租户id")
    private String tenantId;
}
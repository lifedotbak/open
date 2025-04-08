package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/** 流程抄送数据--用户和实例唯一值查询类 */
@Data
@Accessors(chain = true)
@Schema(name = "SysProcessInstanceUserCopySearch对象", description = "流程抄送数据--用户和实例唯一值Search对象")
public class SysProcessInstanceUserCopySearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "发起人")
    private String startUserId;

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "实例id")
    private String processInstanceId;

    @Schema(description = "分组id")
    private String groupId;

    @Schema(description = "分组名称")
    private String groupName;

    @Schema(description = "流程名称")
    private String processName;

    @Schema(description = "抄送人id")
    private String userId;

    @Schema(description = "租户id")
    private String tenantId;
}

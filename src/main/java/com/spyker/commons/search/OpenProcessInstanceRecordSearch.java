package com.spyker.commons.search;

// @formatter:off

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 流程记录查询类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenProcessInstanceRecordSearch对象", description = "流程记录Search对象")
public class OpenProcessInstanceRecordSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "流程名字")
    private String name;

    @Schema(description = "头像")
    private String logo;

    @Schema(description = "发起人的用户id")
    private String userId;

    @Schema(description = "发起人主部门id")
    private String mainDeptId;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "流程实例id")
    private String processInstanceId;

    @Schema(description = "流程实例业务编码")
    private String processInstanceBizCode;

    @Schema(description = "流程实例业务key")
    private String processInstanceBizKey;

    @Schema(description = "表单数据")
    private String formData;

    @Schema(description = "组id")
    private Long groupId;

    @Schema(description = "组名称")
    private String groupName;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "结束时间")
    private Date endTime;

    @Schema(description = "上级流程实例id")
    private String parentProcessInstanceId;

    @Schema(description = "")
    private String process;

    @Schema(description = "结果")
    private Integer result;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "主流程的节点执行id")
    private String parentProcessNodeExecutionId;
}
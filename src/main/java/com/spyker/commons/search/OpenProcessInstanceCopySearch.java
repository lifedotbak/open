package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 流程抄送数据查询类
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenProcessInstanceCopySearch对象", description = "流程抄送数据Search对象")
public class OpenProcessInstanceCopySearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = " 流程发起时间")
    private Date startTime;

    @Schema(description = "当前节点时间")
    private Date nodeTime;

    @Schema(description = "发起人")
    private String startUserId;

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "实例id")
    private String processInstanceId;

    @Schema(description = "节点id")
    private String nodeId;

    @Schema(description = "分组id")
    private Long groupId;

    @Schema(description = "分组名称")
    private String groupName;

    @Schema(description = "流程名称")
    private String processName;

    @Schema(description = "节点 名称")
    private String nodeName;

    @Schema(description = "表单数据")
    private String formData;

    @Schema(description = "抄送人id")
    private String userId;

    @Schema(description = "租户id")
    private String tenantId;
}
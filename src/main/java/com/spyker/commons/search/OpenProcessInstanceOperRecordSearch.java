package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流程记录查询类
 *
 * @author 121232224@qq.com
 * @since 2024-07-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "OpenProcessInstanceOperRecordSearch对象", description = "流程记录Search对象")
public class OpenProcessInstanceOperRecordSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "用户id")
    private String userId;

    @Schema(description = "逻辑删除字段")
    private Boolean delFlag;

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "节点id")
    private String nodeId;

    @Schema(description = "节点名称")
    private String nodeName;

    @Schema(description = "流程实例id")
    private String processInstanceId;

    @Schema(description = "评论")
    private String comment;

    @Schema(description = "操作类型")
    private String operType;

    @Schema(description = "操作描述")
    private String operDesc;

    @Schema(description = "图片列表")
    private String imageList;

    @Schema(description = "文件列表")
    private String fileList;

    @Schema(description = "租户id")
    private String tenantId;
}

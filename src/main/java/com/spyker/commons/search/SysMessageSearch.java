package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/** 通知消息查询类 */
@Data
@Accessors(chain = true)
@Schema(name = "SysMessageSearch对象", description = "通知消息Search对象")
public class SysMessageSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "类型")
    private String messageType;

    @Schema(description = "是否已读")
    private Integer readed;

    @Schema(description = "用户id")
    private String userId;

    @Schema(description = "业务唯一id")
    private String bizUniqueId;

    @Schema(description = "消息参数")
    private String param;

    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "消息头")
    private String title;

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "流程实例id")
    private String processInstanceId;

    @Schema(description = "租户id")
    private String tenantId;
}

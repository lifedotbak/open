package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/** 用户与岗位关联表查询类 */
@Data
@Accessors(chain = true)
@Schema(name = "SysUserPostSearch对象", description = "用户与岗位关联表Search对象")
public class SysUserPostSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "岗位ID")
    private String postId;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;
}

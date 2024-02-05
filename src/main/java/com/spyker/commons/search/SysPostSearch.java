package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 岗位信息表
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@Schema(name = "SysPostSearch对象", description = "岗位信息表Search对象")
public class SysPostSearch {

    private Integer page = 1;

    private Integer size = 10;

    @Schema(description = "岗位ID")
    private String postId;

    @Schema(description = "岗位编码")
    private String postCode;

    @Schema(description = "岗位名称")
    private String postName;

    @Schema(description = "状态（0正常 1停用）")
    private String status;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "备注")
    private String remark;
}
package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 岗位信息表查询类
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysPostSearch对象", description = "岗位信息表Search对象")
public class SysPostSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "岗位编码")
    private String postCode;

    @Schema(description = "岗位名称")
    private String postName;

    @Schema(description = "显示顺序")
    private Integer postSort;

    @Schema(description = "状态（0正常 1停用）")
    private String status;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "备注")
    private String remark;
}

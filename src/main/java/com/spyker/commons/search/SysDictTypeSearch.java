package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 字典类型表查询类
 *
 * @author 121232224@qq.com
 * @since 2024-07-23
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysDictTypeSearch对象", description = "字典类型表Search对象")
public class SysDictTypeSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "字典名称")
    private String dictName;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "状态（0正常 1停用）")
    private String status;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "备注")
    private String remark;
}

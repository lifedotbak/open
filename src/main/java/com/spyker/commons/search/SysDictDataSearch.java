package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/** 字典数据表查询类 */
@Data
@Accessors(chain = true)
@Schema(name = "SysDictDataSearch对象", description = "字典数据表Search对象")
public class SysDictDataSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "字典编码")
    private String dictCode;

    @Schema(description = "字典排序")
    private Integer dictSort;

    @Schema(description = "字典标签")
    private String dictLabel;

    @Schema(description = "字典键值")
    private String dictValue;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "样式属性（其他样式扩展）")
    private String cssClass;

    @Schema(description = "表格回显样式")
    private String listClass;

    @Schema(description = "是否默认（Y是 N否）")
    private String isDefault;

    @Schema(description = "状态（0正常 1停用）")
    private String status;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "备注")
    private String remark;
}

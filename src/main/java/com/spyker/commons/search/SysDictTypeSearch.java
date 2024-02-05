package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 字典类型表
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@Schema(name = "SysDictTypeSearch对象", description = "字典类型表Search对象")
public class SysDictTypeSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "字典主键")
    private String dictId;

    @Schema(description = "字典名称")
    private String dictName;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "状态（0正常 1停用）")
    private String status;
}
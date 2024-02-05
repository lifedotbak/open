package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 字典数据表
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@Schema(name = "SysDictDataSearch对象", description = "字典数据表Search对象")
public class SysDictDataSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "字典标签")
    private String dictLabel;
}
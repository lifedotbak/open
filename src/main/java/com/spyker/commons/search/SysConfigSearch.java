package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/** 参数配置表查询类 */
@Data
@Accessors(chain = true)
@Schema(name = "SysConfigSearch对象", description = "参数配置表Search对象")
public class SysConfigSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "参数名称")
    private String configName;

    @Schema(description = "参数键名")
    private String configKey;

    @Schema(description = "参数键值")
    private String configValue;

    @Schema(description = "系统内置（Y是 N否）")
    private String configType;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "备注")
    private String remark;
}

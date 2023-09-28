package com.spyker.application.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data

@Schema(name = "SysConfigSearch对象", description = "参数配置表Search对象")
public class SysConfigSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "参数主键")
    private String configId;

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

    @Schema(description = "更新时间")
    private Timestamp updateTime;

    @Schema(description = "备注")
    private String remark;

}
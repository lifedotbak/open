package com.spyker.application.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data

@Schema(name = "SysDictTypeSearch对象", description = "字典类型表Search对象")
public class SysDictTypeSearch implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "更新时间")
    private Timestamp updateTime;

    @Schema(description = "备注")
    private String remark;

}
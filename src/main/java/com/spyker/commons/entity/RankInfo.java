package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Rank表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-11-28
 */
@Data
@TableName("rank_info")
@Schema(name = "RankInfo", description = "Rank表对象")
public class RankInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private Integer rank;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
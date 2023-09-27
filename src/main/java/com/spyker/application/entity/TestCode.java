package com.spyker.application.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 *
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-14
 */
@Data
@TableName("test_code")
@Schema(name = "TestCode", description = "$!{table.comment}")
public class TestCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp modifyTime;
}
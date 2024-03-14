package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门表
 *
 * @author CodeGenerator
 * @since 2023-12-25
 */
@Data
@Accessors(chain = true)
@TableName("sys_company")
@Schema(name = "SysCompany", description = "公司表对象")
public class SysCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "公司id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "公司名称")
    private String companyName;

    @Schema(description = "显示顺序")
    private Integer orderNum;

    @Schema(description = "公司状态（0正常 1停用）")
    private String status;

    @Schema(description = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    @Schema(description = "创建者")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
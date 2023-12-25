package com.spyker.commons.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@TableName("sys_dept")
@Schema(name = "SysDept", description = "部门表")
public class SysDept implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "部门id")
	@TableId(value = "dept_id", type = IdType.ASSIGN_ID)
	private String deptId;

	@Schema(description = "父部门id")
	private String parentId;

	@Schema(description = "祖级id路径(parentid/sonid/id)")
	private String ancestors;

	@Schema(description = "部门名称")
	private String deptName;

	@Schema(description = "显示顺序")
	private Integer orderNum;

	@Schema(description = "负责人")
	private String leader;

	@Schema(description = "联系电话")
	private String phone;

	@Schema(description = "邮箱")
	private String email;

	@Schema(description = "部门状态（0正常 1停用）")
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
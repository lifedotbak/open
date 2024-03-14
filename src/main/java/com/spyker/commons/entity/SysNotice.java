package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.*;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知公告表
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@TableName("sys_notice")
@Schema(name = "SysNotice", description = "通知公告表")
public class SysNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "公告ID")
    @TableId(value = "notice_id", type = IdType.ASSIGN_UUID)
    private String noticeId;

    @Schema(description = "公告标题")
    private String noticeTitle;

    @Schema(description = "公告类型（1通知 2公告）")
    private String noticeType;

    @Schema(description = "公告内容")
    private byte[] noticeContent;

    @Schema(description = "公告状态（0正常 1关闭）")
    private String status;

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

    @Schema(description = "备注")
    private String remark;
}
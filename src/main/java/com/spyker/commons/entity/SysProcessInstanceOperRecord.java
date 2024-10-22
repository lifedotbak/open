package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

/**
 * 流程记录
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@RedisHash
@Data
@Accessors(chain = true)
@TableName("sys_process_instance_oper_record")
@Schema(name = "SysProcessInstanceOperRecord", description = "流程记录对象")
public class SysProcessInstanceOperRecord {

    @Schema(description = "PK")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "用户id")
    private String userId;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "创建者")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Schema(description = "流程id")
    private String flowId;

    @Schema(description = "节点id")
    private String nodeId;

    @Schema(description = "节点名称")
    private String nodeName;

    @Schema(description = "流程实例id")
    private String processInstanceId;

    @Schema(description = "评论")
    private String comment;

    @Schema(description = "操作类型")
    private String operType;

    @Schema(description = "操作描述")
    private String operDesc;

    @Schema(description = "图片列表")
    private String imageList;

    @Schema(description = "文件列表")
    private String fileList;

    @Schema(description = "租户id")
    private String tenantId;
}
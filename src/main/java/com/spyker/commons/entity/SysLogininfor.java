package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统访问记录
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@TableName("sys_logininfor")
@Schema(name = "SysLogininfor", description = "系统访问记录")
public class SysLogininfor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "访问ID")
    @TableId(value = "info_id", type = IdType.ASSIGN_UUID)
    private String infoId;

    @Schema(description = "用户账号")
    private String userName;

    @Schema(description = "登录IP地址")
    private String ipaddr;

    @Schema(description = "登录地点")
    private String loginLocation;

    @Schema(description = "浏览器类型")
    private String browser;

    @Schema(description = "操作系统")
    private String os;

    @Schema(description = "登录状态（0成功 1失败）")
    private String status;

    @Schema(description = "提示消息")
    private String msg;

    @Schema(description = "访问时间")
    private Date loginTime;
}
package com.spyker.application.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 操作日志记录
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@TableName("sys_oper_log")
@Schema(name = "SysOperLog", description = "$!{table.comment}")
public class SysOperLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "日志主键")
    @TableId(value = "oper_id", type = IdType.ASSIGN_ID)
    private String operId;

    @Schema(description = "模块标题")
    private String title;

    @Schema(description = "业务类型（0其它 1新增 2修改 3删除）")
    private Integer businessType;

    @Schema(description = "方法名称")
    private String method;

    @Schema(description = "请求方式")
    private String requestMethod;

    @Schema(description = "操作类别（0其它 1后台用户 2手机端用户）")
    private Integer operatorType;

    @Schema(description = "操作人员")
    private String operName;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "请求URL")
    private String operUrl;

    @Schema(description = "主机地址")
    private String operIp;

    @Schema(description = "操作地点")
    private String operLocation;

    @Schema(description = "请求参数")
    private String operParam;

    @Schema(description = "返回参数")
    private String jsonResult;

    @Schema(description = "操作状态（0正常 1异常）")
    private Integer status;

    @Schema(description = "错误消息")
    private String errorMsg;

    @Schema(description = "操作时间")
    private Timestamp operTime;

    @Schema(description = "消耗时间")
    private Long costTime;
}
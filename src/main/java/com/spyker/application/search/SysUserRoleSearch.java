package com.spyker.application.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data

@Schema(name = "SysUserRoleSearch对象", description = "用户和角色关联表Search对象")
public class SysUserRoleSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "角色ID")
    private String roleId;

}
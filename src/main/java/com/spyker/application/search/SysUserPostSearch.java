package com.spyker.application.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户与岗位关联表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data

@Schema(name = "SysUserPostSearch对象", description = "用户与岗位关联表Search对象")
public class SysUserPostSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "岗位ID")
    private String postId;

}
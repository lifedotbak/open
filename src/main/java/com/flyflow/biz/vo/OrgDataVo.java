package com.flyflow.biz.vo;

import com.flyflow.common.constants.StatusEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 组织对象 */
@Schema(description = "组织对象")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgDataVo {

    /** 用户od */
    @Schema(description = "用户od")
    private String id;

    /** 用户名称 */
    @Schema(description = "用户名称")
    private String name;

    /** 类型 */
    @Schema(description = "类型")
    private String type;

    /** 选择 */
    @Schema(description = "选择")
    private Boolean selected;

    /** 头像 */
    @Schema(description = "头像")
    private String avatar;

    /** 用户状态 {@link StatusEnum} */
    @Schema(description = "用户状态 {@link StatusEnum}")
    private Integer status;
}
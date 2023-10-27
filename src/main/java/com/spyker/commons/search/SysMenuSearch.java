package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@Schema(name = "SysMenuSearch对象", description = "菜单权限表Search对象")
public class SysMenuSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "菜单ID")
    private String menuId;

    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "父菜单ID")
    private String parentId;

    @Schema(description = "显示顺序")
    private Integer orderNum;

    @Schema(description = "路由地址")
    private String path;

    @Schema(description = "组件路径")
    private String component;

    @Schema(description = "路由参数")
    private String query;

    @Schema(description = "是否为外链（0是 1否）")
    private Integer isFrame;

    @Schema(description = "是否缓存（0缓存 1不缓存）")
    private Integer isCache;

    @Schema(description = "菜单类型（M目录 C菜单 F按钮）")
    private String menuType;

    @Schema(description = "菜单状态（0显示 1隐藏）")
    private String visible;

    @Schema(description = "菜单状态（0正常 1停用）")
    private String status;

    @Schema(description = "权限标识")
    private String perms;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "备注")
    private String remark;

}
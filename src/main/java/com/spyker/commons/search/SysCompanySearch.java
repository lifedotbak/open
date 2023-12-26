package com.spyker.commons.search;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-12-25
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysCompanySearch对象", description = "部门表Search对象")
public class SysCompanySearch implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer page = 1;
	private Integer size = 10;

	@Schema(description = "公司名称")
	private String companyName;

	@Schema(description = "显示顺序")
	private Integer orderNum;

	@Schema(description = "公司状态（0正常 1停用）")
	private String status;

	@Schema(description = "删除标志（0代表存在 2代表删除）")
	private String delFlag;

	@Schema(description = "创建者")
	private String createBy;

	@Schema(description = "更新者")
	private String updateBy;

}
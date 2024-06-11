package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 通知公告表
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@Data
@Schema(name = "SysNoticeSearch对象", description = "通知公告表Search对象")
public class SysNoticeSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "公告ID")
    private String noticeId;

    @Schema(description = "公告标题")
    private String noticeTitle;

    @Schema(description = "公告类型（1通知 2公告）")
    private String noticeType;

    @Schema(description = "公告状态（0正常 1关闭）")
    private String status;
}
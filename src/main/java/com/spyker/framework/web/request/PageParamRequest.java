package com.spyker.framework.web.request;

import com.spyker.framework.constants.CommonsConstants;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Data
public class PageParamRequest {

    @Schema(description = "页码")
    private int page = CommonsConstants.DEFAULT_PAGE;

    @Schema(description = "每页数量")
    private int size = CommonsConstants.DEFAULT_SIZE;
}
package com.spyker.framework.request;

import com.spyker.framework.constant.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PageParamRequest {

    @Schema(description = "页码")
    private int page = Constants.DEFAULT_PAGE;

    @Schema(description = "每页数量")
    private int size = Constants.DEFAULT_SIZE;
}
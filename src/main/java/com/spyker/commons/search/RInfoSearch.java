package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-11-24
 */
@Data
@Accessors(chain = true)
@Schema(name = "RInfoSearch对象", description = "Search对象")
public class RInfoSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "")
    private String name;

    @Schema(description = "")
    private Date createDate;

    @Schema(description = "")
    private Date modifyDate;

    @Schema(description = "")
    private String info;

}
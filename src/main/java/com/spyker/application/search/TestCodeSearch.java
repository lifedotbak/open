package com.spyker.application.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-14
 */
@Data

@Schema(name = "TestCodeSearch对象", description = "Search对象")
public class TestCodeSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "")
    private String name;

}
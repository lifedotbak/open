package com.spyker.commons.search;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * Rank表
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-11-28
 */
@Data
@Accessors(chain = true)
@Schema(name = "RankInfoSearch对象", description = "Rank表Search对象")
public class RankInfoSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Schema(description = "")
    private String name;

    @Schema(description = "")
    private Integer rank;

}
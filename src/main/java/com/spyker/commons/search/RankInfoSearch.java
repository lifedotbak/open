package com.spyker.commons.search;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.experimental.Accessors;

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
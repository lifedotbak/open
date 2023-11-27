package com.spyker.commons.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-11-24
 */
@Data
@TableName("r_info")
@Schema(name = "RInfo", description = "对象")
public class RInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private Date createDate;

    private Date modifyDate;

    private String info;
}
package com.spyker.application.search;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
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
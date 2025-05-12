package com.spyker.commons.excel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spyker.framework.poi.annotation.Excel;

import lombok.Data;

@JsonIgnoreProperties
@JsonFormat
@Data
public class ExcelTestData {

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "年龄")
    private String age;

    @Excel(name = "性别")
    private String sex;
}

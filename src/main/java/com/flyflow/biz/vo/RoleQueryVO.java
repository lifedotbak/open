package com.flyflow.biz.vo;

import com.flyflow.common.dto.PageDto;

import lombok.Data;

@Data
public class RoleQueryVO extends PageDto {
    /** 用户状态 1在职 2离职 */
    private Integer status;

    private String keywords;
}
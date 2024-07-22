package com.flyflow.biz.vo;

import com.flyflow.common.dto.PageDto;

import lombok.Data;

import java.util.List;

@Data
public class UserListQueryVO extends PageDto {
    /** 用户状态 1在职 2离职 */
    private Integer status;

    private String name;
    private String keywords;

    /** 部门id集合 */
    private List<String> deptIdList;

    /** 部门id */
    private String deptId;
}
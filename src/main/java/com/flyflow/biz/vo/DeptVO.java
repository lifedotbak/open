package com.flyflow.biz.vo;

import com.flyflow.biz.entity.Dept;
import com.flyflow.common.dto.flow.NodeUser;

import lombok.Data;

import java.util.List;

/** 部门vo */
@Data
public class DeptVO extends Dept {
    /** 部门主管 */
    private List<NodeUser> leaderUser;
}
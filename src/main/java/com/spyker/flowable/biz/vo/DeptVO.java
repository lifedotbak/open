package com.spyker.flowable.biz.vo;

import com.spyker.flowable.biz.entity.Dept;
import com.spyker.flowable.common.dto.flow.NodeUser;

import lombok.Data;

import java.util.List;

/** 部门vo */
@Data
public class DeptVO extends Dept {
    /** 部门主管 */
    private List<NodeUser> leaderUser;
}
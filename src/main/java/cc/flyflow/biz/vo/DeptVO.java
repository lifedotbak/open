package cc.flyflow.biz.vo;

import cc.flyflow.biz.entity.Dept;
import cc.flyflow.common.dto.flow.NodeUser;

import lombok.Data;

import java.util.List;

/** 部门vo */
@Data
public class DeptVO extends Dept {
    /** 部门主管 */
    private List<NodeUser> leaderUser;
}
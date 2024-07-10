package com.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 流程节点数据
 *
 * @author Vincent
 * @since 2023-07-06
 */
@Getter
@Setter
@Accessors(chain = true)
public class ProcessNodeData extends BaseEntity {

    /** 流程id */
    @TableField("`flow_id`")
    private String flowId;

    /** 表单数据 */
    @TableField("`data`")
    private String data;

    @TableField("`node_id`")
    private String nodeId;
}
package com.flyflow.common.dto.flow.node;

import com.flyflow.common.dto.flow.Node;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;

/**
 * @author zhj
 * @version 1.0
 * @description: TODO
 * @date 2024/4/3 16:56
 */
@JsonTypeName("-1")
@Data
public class EndNode extends Node {}
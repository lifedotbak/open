package cc.flyflow.common.dto.flow.node;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;

/**
 * @author zhj
 * @version 1.0
 * @description: TODO
 * @date 2024/4/3 16:56
 */
@JsonTypeName("4")
@Data
public class ExclusiveGatewayNode extends GatewayNode {}
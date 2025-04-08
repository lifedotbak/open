package com.spyker.framework.zlmediakit.config;

import io.github.lunasaw.zlm.config.ZlmNode;
import io.github.lunasaw.zlm.enums.LoadBalancerEnums;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/** 解决zlm-spring-boot-starter(1.0.3)当前springboot(2.7.18)版本于系统springboot版本(3.2.4)不兼容的问题 */
@ConfigurationProperties(prefix = "zlm")
@Data
@Component
@Slf4j
public class ExZlmProperties {

    /** 对外NodeMap */
    public static Map<String, ZlmNode> nodeMap = new ConcurrentHashMap<>();

    public static List<ZlmNode> nodes = new CopyOnWriteArrayList<>();

    private final boolean enable = true;

    private final LoadBalancerEnums balance = LoadBalancerEnums.ROUND_ROBIN;

    public Map<String, ZlmNode> getNodeMap() {

        nodeMap =
                nodes.stream()
                        .filter(ZlmNode::isEnabled)
                        .collect(Collectors.toMap(ZlmNode::getServerId, node -> node));

        return nodeMap;
    }

    public List<ZlmNode> getNodes() {
        return nodes;
    }

    public static void addNode(ZlmNode zlmNode) {

        log.info("add node:{}", zlmNode);

        if (zlmNode != null) {
            nodes.add(zlmNode);
            nodeMap.put(zlmNode.getServerId(), zlmNode);
        }
    }
}

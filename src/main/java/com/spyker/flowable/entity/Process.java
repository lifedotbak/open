package com.spyker.flowable.entity;

import lombok.Data;

@Data
public class Process {
    String id;

    String deploymentId;

    String name;

    String resourceName;

    String key;

    String diagramresourceName;

    Integer version;

    Boolean suspended;
}
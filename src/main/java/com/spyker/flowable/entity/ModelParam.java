package com.spyker.flowable.entity;

import lombok.Data;

@Data
public class ModelParam {

    protected String name;

    protected String key;

    protected String category;

    protected Integer version;

    protected String description;
}
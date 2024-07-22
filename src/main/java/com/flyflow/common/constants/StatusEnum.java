package com.flyflow.common.constants;

import lombok.Getter;

/** 状态枚举 */
public enum StatusEnum implements IBaseEnum<Integer> {
    ENABLE(1, "启用"),
    DISABLE(0, "禁用");

    @Getter private final Integer value;

    @Getter private final String label;

    StatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
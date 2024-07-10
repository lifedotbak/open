package com.flyflow.common.constants;

import lombok.Getter;

import java.util.Arrays;

/** 操作类型 */
@Getter
public enum OperTypeEnum {
    START("开始", "start"),

    PASS("同意", "pass"),
    REFUSE("拒绝", "refuse"),
    CANCEL("取消", "cancel"),
    ;

    private String name;

    OperTypeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static OperTypeEnum getByValue(String value) {
        return Arrays.stream(OperTypeEnum.values())
                .filter(w -> w.getValue().equals(value))
                .findAny()
                .orElse(null);
    }

    private String value;
}
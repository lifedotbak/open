package com.flyflow.common.constants;

import lombok.Getter;

import java.util.Arrays;

/** 任务类型 */
@Getter
public enum TaskTypeEnum {
    PASS("同意", "pass"),
    RESOLVE("同意", "resolve"),
    REFUSE("拒绝", "refuse"),
    CANCEL("取消", "cancel"),
    ;

    private String name;

    TaskTypeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static TaskTypeEnum getByValue(String value) {
        return Arrays.stream(TaskTypeEnum.values())
                .filter(w -> w.getValue().equals(value))
                .findAny()
                .orElse(null);
    }

    private String value;
}
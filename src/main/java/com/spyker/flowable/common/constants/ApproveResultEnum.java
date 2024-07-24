package com.spyker.flowable.common.constants;

import lombok.Getter;

import java.util.Arrays;

/** 审批结果 */
@Getter
public enum ApproveResultEnum implements IBaseEnum<Integer> {
    PASS("同意", 1),
    REFUSE("拒绝", 2),
    CANCEL("撤销", 3),
    ;

    private final String label;
    private final Integer value;

    ApproveResultEnum(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public static ApproveResultEnum get(int code) {
        return Arrays.stream(ApproveResultEnum.values())
                .filter(w -> w.getValue() == code)
                .findAny()
                .get();
    }
}
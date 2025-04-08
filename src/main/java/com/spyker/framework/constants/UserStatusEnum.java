package com.spyker.framework.constants;

/** 用户状态 */
public enum UserStatusEnum {
    OK("0", "正常"),

    DISABLE("1", "停用"),

    DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatusEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}

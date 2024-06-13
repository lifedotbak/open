package com.spyker.framework.aliyun.push.enums;

public enum PushTargetType {
    DEVICE("DEVICE", "按设备"),

    ALIAS("ALIAS", "别名"),

    ACCOUNT("ACCOUNT", "帐号"),

    TAG("TAG", "按标签推送"),

    ALL("TAG", "广播推送");

    private final String type;
    private final String desc;

    PushTargetType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
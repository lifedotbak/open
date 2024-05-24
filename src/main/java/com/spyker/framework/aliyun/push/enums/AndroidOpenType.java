package com.spyker.framework.aliyun.push.enums;

public enum AndroidOpenType {
    APPLICATION("APPLICATION", "打开应用"),
    ACTIVITY("ACTIVITY", "打开AndroidActivity"),
    URL("URL", "打开URL"),
    NONE("ACTIVITY", "无跳转");

    private final String type;
    private final String desc;

    AndroidOpenType(String type, String desc) {
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
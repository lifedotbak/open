package com.spyker.framework.third.aliyun.push.enums;

public enum PushMessageType {
    NOTICE("NOTICE", "通知"),
    MESSAGE("MESSAGE", "消息");

    private final String type;
    private final String desc;

    PushMessageType(String type, String desc) {
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
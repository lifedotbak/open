package com.spyker.iot.vmp.vmanager.bean;

public enum PlayTypeEnum {
    PLAY("0", "直播"),
    PLAY_BACK("1", "回放");

    private final String value;
    private final String name;

    PlayTypeEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
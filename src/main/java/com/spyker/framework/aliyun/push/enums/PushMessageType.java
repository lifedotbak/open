package com.spyker.framework.aliyun.push.enums;

public enum PushMessageType {

	NOTICE("NOTICE", "通知")

	, MESSAGE("MESSAGE", "消息")

	;

	private String type;
	private String desc;

	private PushMessageType(String type, String desc) {
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

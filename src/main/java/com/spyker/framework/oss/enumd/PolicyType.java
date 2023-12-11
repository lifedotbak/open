package com.spyker.framework.oss.enumd;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PolicyType {

	/**
	 * 只读
	 */
	READ("read-only"),

	/**
	 * 只写
	 */
	WRITE("write-only"),

	/**
	 * 读写
	 */
	READ_WRITE("read-write");

	/**
	 * 类型
	 */
	private final String type;

}

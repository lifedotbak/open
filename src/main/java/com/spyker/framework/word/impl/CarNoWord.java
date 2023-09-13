
package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

/**
 * 车牌号脱敏（1个汉字+1个字母+5位字母和数字组合流水号，流水号前为地区编码，保留地区编码和流水号最后2位，其余用*代替）
 */
public class CarNoWord {

	private static final String carNoRegx = "(^[\\u4e00-\\u9fa5]{1})(\\w{1})(\\w{3,4})(\\w{2})";
	private static final String carNoReplaceRule = "$1$2***$4";

	public static String getValue(String word) {

		word = word.toUpperCase();
		boolean checked = SensitiveWordUtils.carId(word);
		if (!checked) {
			return word;
		}
		return word.replaceAll(carNoRegx, carNoReplaceRule);
	}
}


package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

/**
 * 企业户名
 * 按长度分阶梯保留：长度4个字及以下的，首尾各保留1个字；长度5-6个字的，首尾各保留2个字；长度7个字及以上奇数，隐去中间3个字；长度8个字及以上偶数，隐去中间4个字；隐藏字用*代替。
 */
public class CompanyNameWord {

	public static String getValue(String word) {
		boolean checked = SensitiveWordUtils.isChinese(word);
		if (!checked) {
			return word;
		}
		int length = word.length();
		String second = "";
		if (length <= 4) {
			String first = word.substring(0, 1);
			String third = word.substring(length - 1);
			for (int i = 1; i < length - 1; i++) {
				second = second.concat("*");
			}
			return first.concat(second).concat(third);
		} else if (length == 5 || length == 6) {
			String first = word.substring(0, 2);
			String third = word.substring(length - 2);
			for (int i = 3; i < length - 1; i++) {
				second = second.concat("*");
			}
			return first.concat(second).concat(third);
		} else if (length >= 7 && (length % 2 == 1)) {
			String first = word.substring(0, (length - 3) / 2);
			String third = word.substring(length - (length - 3) / 2);
			for (int i = 0; i < 3; i++) {
				second = second.concat("*");
			}
			return first.concat(second).concat(third);
		} else if (length >= 8 && (length % 2 == 0)) {
			String first = word.substring(0, (length - 4) / 2);
			String third = word.substring(length - (length - 4) / 2);
			for (int i = 0; i < 4; i++) {
				second = second.concat("*");
			}
			return first.concat(second).concat(third);
		} else {
			return word;
		}
	}
}

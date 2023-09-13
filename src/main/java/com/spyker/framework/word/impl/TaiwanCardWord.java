package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 18-6-6 Time: 上午9:52 To
 * change this template use File | Settings | File Templates.
 */
public class TaiwanCardWord {

	private static final String taiwanCardRegx = "(\\d{4})(\\d{4})";
	private static final String taiwanCardReplace = "*****$2";

	public static String getValue(String word) {

		boolean checked = SensitiveWordUtils.isNum(word);
		if (!checked) {
			return word;
		}

		return word.replaceAll(taiwanCardRegx, taiwanCardReplace);
	}
}

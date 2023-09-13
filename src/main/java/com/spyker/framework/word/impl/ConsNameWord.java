package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

/**
 * 普通户名 、联系人姓名
 * (3个字以内隐藏第1个字，4-6个字隐藏前2个字，大于6个字隐藏第3-6个字，隐藏字用*代替；对于姓名用“•”分为多部分的情况，每部分均采用上述规则进行脱敏。)
 */
public class ConsNameWord {

	private static final int consNameMinLength = 3;
	private static final int consNameCenterLenght = 4;
	private static final int consNameMaxLength = 6;
	private static final String consNameSplit = "•";
	private static final String repalceStr = "*";

	public static String getValue(String word) {
		boolean checked = SensitiveWordUtils.isChineseName(word);
		if (!checked) {
			return word;
		}
		// 判断是否有以"•"分隔
		word = word.replace("·", "•");
		String[] words = word.split(consNameSplit);
//        System.out.println(words.length);
		StringBuilder returnWord = new StringBuilder();
		for (String word2 : words) {
//            System.out.println(words[i]);
			int length = word2.length();
			if (length <= consNameMinLength && length > 0) {
				returnWord.append(repalceStr).append(word2.substring(1, length));
			} else if (length >= consNameCenterLenght && length <= consNameMaxLength) {
				returnWord.append(repalceStr).append(repalceStr).append(word2.substring(2, length));
			} else if (length > consNameMaxLength) {
				returnWord.append(word2.substring(0, 2)).append(repalceStr).append(repalceStr).append(repalceStr)
						.append(repalceStr).append(word2.substring(5, length));
			}
			returnWord.append(consNameSplit);

		}
		return returnWord.substring(0, returnWord.length() - 1);
	}
}

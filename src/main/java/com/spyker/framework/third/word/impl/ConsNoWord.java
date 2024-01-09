package com.spyker.framework.third.word.impl;

import com.spyker.framework.third.word.SensitiveWordUtils;

/** 客户编号脱敏(保留前3位和最后3位，其余用*代替。) 长度为10 */
public class ConsNoWord {

    private static final int consNoLength = 10;
    private static final String consNoReg = "(\\d{3})\\d{4}(\\d{3})";
    private static final String consNoReplaceRule = "$1****$2";

    public static String getValue(String word) {
        boolean checked = SensitiveWordUtils.isNumeric(word);
        if (!checked) {
            return word;
        }
        int length = word.length();
        if (length == consNoLength) {
            return word.replaceAll(consNoReg, consNoReplaceRule);
        } else {
            return word;
        }
    }
}
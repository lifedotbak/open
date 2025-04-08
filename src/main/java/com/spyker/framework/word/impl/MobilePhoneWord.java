package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

/** 手机号脱敏(保留前3位和最后3位，其余用*代替。) */
public class MobilePhoneWord {

    private static final int phoneLength = 11;
    private static final String phoneReg = "(\\d{3})\\d{5}(\\d{3})";
    private static final String phoneReplaceRule = "$1*****$2";

    public static String getValue(String word) {
        boolean checked = SensitiveWordUtils.isNumeric(word);
        if (!checked) {
            return word;
        }
        int length = word.length();
        if (length == phoneLength) {
            return word.replaceAll(phoneReg, phoneReplaceRule);
        } else {
            return word;
        }
    }
}

package com.spyker.framework.third.word.impl;

import com.spyker.framework.third.word.SensitiveWordUtils;

/** 电子邮箱脱敏 邮箱名+@+邮件服务地址 (“@”前小于等于5位的，隐藏前2位；大于5位的，保留前3位，其余用*代替。) */
public class EmailWord {

    private static final String emailSplit = "@";
    private static final String replaceChart = "*";

    public static String getValue(String word) {
        boolean checked = SensitiveWordUtils.isEmail(word);
        if (!checked) {
            return word;
        }
        int length = word.length();
        String second = "";
        int splitLength = word.indexOf(emailSplit);
        if (splitLength <= 2) {
            return word;
        } else if (splitLength <= 5) {
            String first = word.substring(2, length);
            for (int i = 0; i < 2; i++) {
                second = second.concat(replaceChart);
            }
            return second.concat(first);
        } else if (splitLength > 5) {
            String first = word.substring(0, 3);
            String third = word.substring(splitLength, length);
            for (int i = 0; i < splitLength - 3; i++) {
                second = second.concat(replaceChart);
            }
            return first.concat(second).concat(third);
        } else {
            return word;
        }
    }
}
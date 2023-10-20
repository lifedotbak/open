package com.spyker.framework.third.word.impl;

/**
 * 联系人地址、法人地址、增值税注册地址（非结构化）脱敏
 * (按长度分阶梯保留：长度5个字及以下的，保留第1个字和最后2个字；长度6-9个字的，保留最后5个字；长度为10个字及以上的，隐去最后5个字之前的4个字；隐藏字用*代替。)
 */
public class ContactAddrWord {

    public static String getValue(String word) {
        int length = word.length();
        String second = "";
        if (length > 0 && length <= 5) {
            String first = word.substring(0, 1);
            String third = word.substring(length - 2);
            for (int i = 2; i < length - 1; i++) {
                second = second.concat("*");
            }
            return first.concat(second).concat(third);
        } else if (length >= 6 && length <= 9) {
            String third = word.substring(length - 5);
            for (int i = 0; i < length - 5; i++) {
                second = second.concat("*");
            }
            return second.concat(third);
        } else if (length >= 10) {
            String first = word.substring(0, length - 9);
            String third = word.substring(length - 5);
            for (int i = 0; i < 4; i++) {
                second = second.concat("*");
            }
            return first.concat(second).concat(third);
        } else {
            return word;
        }
    }
}
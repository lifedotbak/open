package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

/** 军人证号脱敏(汉字和数字组合字符串，军种3个汉字+番号4位数字+流水号4位数字，保留最后3位，其余用*代替) */
public class SoldierCardWord {
    private static final String soldierCardRegx = "(\\d{5})(\\d{3})";
    private static final String soldierCardReplace = "*****$2";

    public static String getValue(String word) {
        if (word.length() == 11) {
            String ch = word.substring(0, 3);
            String num = word.substring(3, 11);
            if (!SensitiveWordUtils.isChinese(ch)) {
                return word;
            }
            if (!SensitiveWordUtils.isNum(num)) {
                return word;
            }
            return ch.concat(num.replaceAll(soldierCardRegx, soldierCardReplace));
        }
        return word;
    }
}

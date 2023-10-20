package com.spyker.framework.third.word.impl;

import com.spyker.framework.third.word.SensitiveWordUtils;

/**
 * 增值税账户脱敏（8-28位数字，为银行对公账户，开户行编号+卡种编号+流水号，保留最后4位，其余用*代替）
 */
public class VatAccountWord {

    public static String getValue(String word) {
        int x = word.length();
        int y = x - 4;
        String z = "";
        for (int i = 0; i < y; i++) {
            z = z + "*";
        }
        if (word.length() > 7 && word.length() < 29) {
            if (SensitiveWordUtils.vatAccountId(word)) {
                return word.replaceAll("(\\d{4,24})(\\d{4})", z + "$2");
            } else {
                return word;
            }
        }
        return word;
    }
}
package com.spyker.framework.third.word.impl;

import com.spyker.framework.third.word.SensitiveWordUtils;

/**
 * 银行卡脱敏（13-19位数字，开户行编号+卡种编号+流水号，保留前4位和最后4位，中间用*代替）
 */
public class BankCardWord {

    public static String getValue(String word) {
        int x = word.length();
        int y = x - 8;
        String z = "";
        for (int i = 0; i < y; i++) {
            z = z + "*";
        }
        if (word.length() > 12 && word.length() < 20) {
            if (SensitiveWordUtils.bankCardId(word)) {
                return word.replaceAll("(\\d{4})(\\d{5,11})(\\d{4})", "$1" + z + "$3");
            } else {
                return word;
            }
        }
        return word;
    }
}
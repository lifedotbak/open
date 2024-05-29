package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

/** 存折账号脱敏（14-19位数字，开户行编号+折种编号+流水号，保留前4位和最后4位，中间用*代替） */
public class BankBookWord {

    public static String getValue(String word) {
        int x = word.length();
        int y = x - 8;
        String z = "";
        for (int i = 0; i < y; i++) {
            z = z + "*";
        }
        if (word.length() > 13 && word.length() < 20) {
            if (SensitiveWordUtils.bankBookId(word)) {
                return word.replaceAll("(\\d{4})(\\d{6,11})(\\d{4})", "$1" + z + "$3");
            } else {
                return word;
            }
        }
        return word;
    }
}
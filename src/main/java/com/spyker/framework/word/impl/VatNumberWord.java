package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

/** 增值税税号脱敏（15-20位不定长数字，2位省市代码+4位地区代码+2位经济性质代码+2位行业代码+流水号，保留前4位和最后4位，其余用*代替） */
public class VatNumberWord {

    public static String getValue(String word) {
        int x = word.length();
        int y = x - 8;
        String z = "";
        for (int i = 0; i < y; i++) {
            z = z + "*";
        }
        if (word.length() > 14 && word.length() < 21) {
            if (SensitiveWordUtils.vatNumberId(word)) {
                return word.replaceAll("(\\d{4})(\\d{7,12})(\\w{4})", "$1" + z + "$3");
            } else {
                return word;
            }
        }
        return word;
    }
}

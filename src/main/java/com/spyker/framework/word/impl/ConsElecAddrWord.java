package com.spyker.framework.word.impl;

/** 用电地址（结构化）脱敏 (取结构化地址“省+市+区县+街道/乡镇+居委会/村+道路+小区+门牌号”中“省+市+区县+门牌号”部分，门牌号保留最后5位，中间用6个*代替。) */
public class ConsElecAddrWord {

    public static String getValue(String word) {
        int q_length = word.length();
        String second = "";
        if (q_length >= 14) {
            String first = word.substring(0, 9);
            String third = word.substring(q_length - 5);
            for (int i = 0; i < 6; i++) {
                second = second.concat("*");
            }
            return first.concat(second).concat(third);
        } else {
            return word;
        }
    }
}

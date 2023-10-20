package com.spyker.framework.third.word.impl;

import java.util.regex.Pattern;

/**
 * 身份证号脱敏(保留前6位和最后4位，其余用*代替)
 */
public class IdCardWord {

    private static final String IdCardReg18 =
            "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}" + "([\\d|x|X]{1})$";
    private static final String IdCardReg = "(\\d{6})\\d{8}(\\w{4})";
    private static final String IdCardReplaceRule = "$1********$2";

    public static String getValue(String word) {
        boolean flag = is18IdCard(word);
        if (flag) {
            return word.replaceAll(IdCardReg, IdCardReplaceRule);
        } else {
            return word;
        }
    }

    /**
     * 18位身份证号码的基本数字和位数验校
     *
     * @param idcard
     * @return
     */
    private static boolean is18IdCard(String idcard) {
        return Pattern.matches(IdCardReg18, idcard);
    }
}
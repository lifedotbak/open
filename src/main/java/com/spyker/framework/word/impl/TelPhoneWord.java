package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

/**
 * 判断是不是固定电话 g格式：区号+3-4位区域信息+4位流水号 d规则：区号不隐藏，7-8位电话号码保留最后3位，其它用*代替
 */
public class TelPhoneWord {

    private static final String phoheSplit = "-";

    public static String getValue(String word) {
        boolean checked = SensitiveWordUtils.isPhone(word);
        if (!checked) {
            return word;
        }
        int length = word.length();
        int splitLenth = word.indexOf(phoheSplit);
        String second = "";
        if (length > 0) {
            String first = word.substring(0, splitLenth + 1);
            String third = word.substring(length - 3);
            for (int i = splitLenth + 1; i < length - 3; i++) {
                second = second.concat("*");
            }
            return first.concat(second).concat(third);
        } else {
            return word;
        }
    }
}
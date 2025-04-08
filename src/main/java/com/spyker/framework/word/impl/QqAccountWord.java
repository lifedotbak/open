package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

/** QQ账户脱敏(5-10位，分段屏蔽，每隔2位用*替换2位。) */
public class QqAccountWord {

    public static String getValue(String word) {
        boolean checked = SensitiveWordUtils.isNumeric(word);
        if (!checked) {
            return word;
        }
        int length = word.length();
        String second = "";
        if (length >= 5 && length <= 10) {
            String first = word.substring(0, 2);
            String third = word.substring(length - 1);
            for (int i = 2; i < length - 1; i++) {
                second = second.concat("*");
            }

            return first.concat(second).concat(third);
        } else {
            return word;
        }
    }
}

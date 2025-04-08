package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

public class PassCardWord {

    private static final String passCardRegx = "(\\d{5})(\\d{3})";
    private static final String passCardReplace = "*****$2";

    public static String getValue(String word) {

        boolean checked = SensitiveWordUtils.isPassCard(word);
        if (!checked) {
            return word;
        }
        String first = word.substring(0, 1);
        String second = word.substring(1);
        checked = SensitiveWordUtils.isNum(second);
        if (!checked) {
            return word;
        }
        return first.concat(second.replaceAll(passCardRegx, passCardReplace));
    }
}

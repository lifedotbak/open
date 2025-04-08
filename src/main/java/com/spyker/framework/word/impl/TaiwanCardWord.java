package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

public class TaiwanCardWord {

    private static final String taiwanCardRegx = "(\\d{4})(\\d{4})";
    private static final String taiwanCardReplace = "*****$2";

    public static String getValue(String word) {

        boolean checked = SensitiveWordUtils.isNum(word);
        if (!checked) {
            return word;
        }

        return word.replaceAll(taiwanCardRegx, taiwanCardReplace);
    }
}

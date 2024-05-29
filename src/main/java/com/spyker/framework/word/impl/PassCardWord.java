package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 18-6-6 Time: 上午9:44 To change this template
 * use File | Settings | File Templates.
 */
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
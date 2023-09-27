package com.spyker.framework.word.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网站账户脱敏(分段屏蔽，每隔2位用*替换2位。)
 */
public class WebAccountWord {

    private static final String replaceChart = "*";
    private static final String regex_web = "[-A-Za-z0-9_.]{5,30}";

    public static String getValue(String word) {
        boolean checked = checkSourceWeb(word);
        if (!checked) {
            return word;
        }
        int len = word.length();

        StringBuilder buffer = new StringBuilder(word.length());
        int index = 0;
        while (index < len) {
            if (index % 4 - 2 == 0 || index % 4 - 3 == 0) {
                buffer.append(replaceChart);
            } else {
                buffer.append(word.charAt(index));
            }
            ++index;
        }

        return buffer.toString();
    }

    private static boolean checkSourceWeb(String word) {
        if (word == null || "".equals(word)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex_web);
        Matcher m = pattern.matcher(word);
        return m.matches();
    }

    public static void main(String[] args) {
        String message = "C0A86EFF48444C4D495241434C45AAAA10280201BC0032FFFF01F8000400D418";
        String par = "^[-A-Za-z0-9]{34}([-A-Za-z0-9]{4})[-A-Za-z0-9]{4}0032[-A-Za-z0-9]{4}([-A-Za-z0-9]{2})F8";

        Pattern pattern = Pattern.compile(par);

        Matcher m = pattern.matcher(message);

        System.out.println(m.matches());

    }
}
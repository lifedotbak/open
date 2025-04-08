package com.spyker.framework.word.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeChatWord {

    private static final String replaceChart = "*";
    private static final String regex_web = "[-A-Za-z0-9_.]{6,20}";

    private static boolean checkSourceWeb(String word) {
        if (word == null || "".equals(word)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex_web);
        Matcher m = pattern.matcher(word);
        return m.matches();
    }

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

    public static void main(String[] args) {

        String par = "^[\\S]{34}netIdDeviceId[\\S]{4}0032[\\S]{4}lineIdF8op.*$";

        par = par.replace("netId", "28");
        par = par.replace("DeviceId", "02");
        par = par.replace("lineId", "01");
        par = par.replace("op", "00");

        Pattern pattern = Pattern.compile(par);
        Matcher m =
                pattern.matcher("C0A86EFF48444C4D495241434C45AAAA10280201BC0032FFFF01F8000400D418");
        System.out.println(m.matches());
    }
}

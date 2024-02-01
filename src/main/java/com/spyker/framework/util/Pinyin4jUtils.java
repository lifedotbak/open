package com.spyker.framework.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * 汉字转化为拼音
 *
 * @author SPYKER
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Pinyin4jUtils {

    public static String convertTolowerCase(String chines) {

        return convert(chines).toLowerCase();
    }

    public static String convert(String chines) {

        if (null == chines || chines.trim().length() < 1) {
            return "";
        }

        try {

            StringBuilder pinyinName = new StringBuilder();
            char[] nameChar = chines.toCharArray();
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            for (int i = 0; i < nameChar.length; i++) {
                if (nameChar[i] > 128) {
                    pinyinName.append(
                            PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0]);
                } else {
                    pinyinName.append(nameChar[i]);
                }
            }
            return pinyinName.toString().toUpperCase();

        } catch (Exception e) {
            log.error("error -->{}", e);
            return "";
        }
    }
}
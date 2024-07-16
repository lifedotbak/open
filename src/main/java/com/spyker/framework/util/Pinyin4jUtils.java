package com.spyker.framework.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉字转化为拼音
 *
 * @author SPYKER
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Pinyin4jUtils {

    public static String getAllPinyin(String hanzi) {
        // 输出格式设置
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        /**
         * 输出大小写设置
         *
         * <p>LOWERCASE:输出小写 UPPERCASE:输出大写
         */
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);

        /**
         * 输出音标设置
         *
         * <p>WITH_TONE_MARK:直接用音标符（必须设置WITH_U_UNICODE，否则会抛出异常） WITH_TONE_NUMBER：1-4数字表示音标
         * WITHOUT_TONE：没有音标
         */
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        /**
         * 特殊音标ü设置
         *
         * <p>WITH_V：用v表示ü WITH_U_AND_COLON：用"u:"表示ü WITH_U_UNICODE：直接用ü
         */
        format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);

        char[] hanYuArr = hanzi.trim().toCharArray();
        StringBuilder pinYin = new StringBuilder();

        try {
            for (int i = 0, len = hanYuArr.length; i < len; i++) {
                // 匹配是否是汉字
                if (Character.toString(hanYuArr[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    // 如果是多音字，返回多个拼音，这里只取第一个
                    String[] pys = PinyinHelper.toHanyuPinyinStringArray(hanYuArr[i], format);
                    pinYin.append(pys[0]);
                } else {
                    pinYin.append(hanYuArr[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return pinYin.toString();
    }

    public static String getFirstPinYin(String hanyu) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuilder firstPinyin = new StringBuilder();
        char[] hanyuArr = hanyu.trim().toCharArray();
        try {
            for (int i = 0, len = hanyuArr.length; i < len; i++) {
                if (Character.toString(hanyuArr[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] pys = PinyinHelper.toHanyuPinyinStringArray(hanyuArr[i], format);
                    firstPinyin.append(pys[0].charAt(0));
                } else {
                    firstPinyin.append(hanyuArr[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return firstPinyin.toString();
    }

    public static void main(String[] args) {
        String pinYin = getAllPinyin("秋水共长天一色");
        String firstPinYin = getFirstPinYin("秋水共长天一色");
        System.out.println("秋水共长天一色全拼：" + pinYin);
        System.out.println("秋水共长天一色首字母：" + firstPinYin);
    }

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
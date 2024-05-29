package com.spyker.framework.word.impl;

import com.spyker.framework.word.SensitiveWordUtils;

/** 车架号脱敏（17位字母和数字组合，3位生产地、车牌、类型+5位车辆特征+3位校验信息+6位流水号，保留最后6位，其余用*代替） */
public class CarVinWord {

    private static final String carVinRegx = "(\\w{11})(\\d{6})";
    private static final String carVinReplaceRule = "***********$2";

    public static String getValue(String word) {
        boolean checked = SensitiveWordUtils.carVin(word);
        if (!checked) {
            return word;
        }
        return word.replaceAll(carVinRegx, carVinReplaceRule);
    }
}
package com.spyker.framework.util.number;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 数字的工具类
 *
 * @author spyker
 */
public class NumberUtils {

    public static Long parseLong(String str) {
        return CharSequenceUtil.isNotEmpty(str) ? Long.valueOf(str) : null;
    }
}
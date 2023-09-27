package com.spyker.framework.util;

import org.apache.commons.lang3.RandomStringUtils;

public final class AppRandomStringUtil {

    public static String random32() {

        return RandomStringUtils.randomAlphanumeric(32);
    }

    public static String random64() {

        return RandomStringUtils.randomAlphanumeric(64);
    }

    public static String random32LowerCase() {

        return RandomStringUtils.randomAlphanumeric(32).toLowerCase();
    }

}
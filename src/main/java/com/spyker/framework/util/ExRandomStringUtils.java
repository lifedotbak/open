package com.spyker.framework.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.RandomStringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExRandomStringUtils extends RandomStringUtils {

    public static String random32() {

        return randomAlphanumeric(32);
    }

    public static String random64() {

        return randomAlphanumeric(64);
    }

    public static String random32LowerCase() {

        return randomAlphanumeric(32).toLowerCase();
    }
}
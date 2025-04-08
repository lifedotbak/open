package com.spyker.framework.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GenerateSecurityCodeUtils {

    private static final int DEFAULT_LENGTH = 6;

    private static final String MIX_BASE_STRING =
            "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String BASE_STRING = "0123456789";

    public static String create() {

        return create(DEFAULT_LENGTH);
    }

    public static String create(int length) {

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(BASE_STRING.length());
            sb.append(BASE_STRING.charAt(number));
        }
        return sb.toString();
    }

    public static String createMix() {

        return createMix(DEFAULT_LENGTH);
    }

    public static String createMix(int length) {

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(MIX_BASE_STRING.length());
            sb.append(MIX_BASE_STRING.charAt(number));
        }
        return sb.toString();
    }
}

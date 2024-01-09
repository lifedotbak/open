package com.spyker.framework.third.mqtt;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.lang.NonNull;

public final class HexConverUtil {

    private HexConverUtil() {}

    /**
     * 接收的队列消息，转hex的string的串
     *
     * @param bytes
     * @return
     */
    public static String hexInPayload2String(@NonNull byte[] bytes) {

        return Hex.toHexString(bytes);
    }

    /**
     * 发送到队列的消息，hex的string串转byte[]
     *
     * @param hexString
     * @return
     */
    public static byte[] hexString2Byte(@NonNull String hexString) {

        hexString = hexString.replace(" ", "");
        return Hex.decode(hexString);
    }

    public static String hex2(long value) {
        return hexLeftPad(value, 2);
    }

    private static String hexLeftPad(long value, int lenght) {

        String result = Long.toHexString(value);

        if (result.length() < lenght) {
            result = StringUtils.leftPad(result, lenght, "0");
        }

        return result;
    }

    public static int converHex210(String value) {

        return Integer.parseInt(value, 16);
    }
}
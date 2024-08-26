package com.spyker.framework.util.sign;

import org.apache.commons.codec.binary.Base64;

/**
 * Base64工具类
 *
 * @author spyker
 */
public final class Base64Utils {

    /**
     * Encodes hex octects into Base64
     *
     * @param binaryData Array containing binaryData
     * @return Encoded Base64 array
     */
    public static String encodeBase64String(byte[] binaryData) {
        return Base64.encodeBase64String(binaryData);
    }

    /**
     * 进行Base64加密
     *
     * @param binaryData
     * @return
     * @throws Exception
     */
    public static byte[] encodeBase64(byte[] binaryData) throws Exception {
        return Base64.encodeBase64(binaryData);
    }

    /**
     * Decodes Base64 data into octects 进行Base64解密
     *
     * @param encoded string containing Base64 data
     * @return Array containind decoded data.
     */
    public static byte[] decodeBase64(String encoded) {
        return Base64.decodeBase64(encoded);
    }
}
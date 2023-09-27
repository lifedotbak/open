package com.spyker.framework.util;

import org.apache.commons.codec.binary.Base64;

public final class Base64Util {

    /**
     * 进行Base64解密
     *
     * @param binaryData
     * @return
     * @throws Exception
     */
    public static byte[] decodeBase64(String binaryData) throws Exception {
        return Base64.decodeBase64(binaryData.getBytes());
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

}
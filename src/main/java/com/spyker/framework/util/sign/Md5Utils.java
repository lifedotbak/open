package com.spyker.framework.util.sign;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Md5加密方法
 *
 * @author spyker
 */
@Slf4j
public class Md5Utils {

    public static String hash(String s) {
        try {
            return new String(
                    toHex(md5(s)).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("not supported charset...{}", e);
            return s;
        }
    }

    private static final String toHex(byte[] hash) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes(StandardCharsets.UTF_8));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
            log.error("MD5 Error...{}", e);
        }
        return null;
    }

    public static String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }

    /**
     * get md5 string from file
     *
     * @param data
     * @return
     */
    public static String md5Hex(final File data) {

        String result = "";

        InputStream is = null;
        if (data.exists() && data.isFile()) {

            try {

                is = new FileInputStream(data);

                result = DigestUtils.md5Hex(is);
            } catch (Exception ex) {
                log.error("md5Hex error-->{}", ex);
            } finally {
                IOUtils.closeQuietly(is);
            }
        }
        return result;
    }
}
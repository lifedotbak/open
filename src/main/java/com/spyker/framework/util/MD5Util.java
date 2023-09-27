package com.spyker.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author ZZF on 2012-4-27
 */
@Slf4j
public final class MD5Util {

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

    private MD5Util() {

    }
}
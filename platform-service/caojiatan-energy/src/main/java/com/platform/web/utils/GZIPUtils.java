package com.platform.web.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;

@Slf4j
public class GZIPUtils {

    public static void main(String[] args) throws Exception {
        String zipFile = "1f8b 0800 ebad 0a65 02ff 7ddd dfaa 2e59 79c5 e15b 917d 1ce5 ab7a c778 6795 a7b9 8540 0e42 90f6 4f8c 206d 821a 22e2 bd67 7720 d02d 79fa 44e9 f5d9 ad35 f762 eddf 969a e3f9 eb97 3ffd e53f 7ef3 e5e7 3ff9 f26f 7ffe fdef bffc c34f befc fa9b 3f7d f3f5 afff e5af 5f7e f7eb afff 7e5d 5fbf f65f dffc fecf dffd 87ae 9f7d fef6 0f3f f9bf 4f6e 7e32 fc24 fca4 fc64 bff7 c9f7 bf7e f0f5 075f 7fff ffaf df1f 7cfd c2d7 6f7c 9d4f 7d07 7f47 f175 3cf1 7df8 df80 67be f1cc f3d1 3f69 f0d4 abaf e334 76f0 759c c5e2 2c16 67b1 f8d5 5f9c c4e2 240e 7ef5 0f9e f7e0 790f 9ef7 e079 0f9e f7e0 790f 9ef7 e079 0f9e f7c1 f33e 78de 07cf fbe0 791f 3cef 83e7 7df0 bc0f 9ef7 c1f3 3e78 de17 cffb e279 5f3c ef8b e77d f1bc 2f9e f7c5 f3be 78de 17cf fbe2 79af cf47 1f5c fae0 d607 a30f a20f aa0f f443 fca3 9fe2 1ffd 18ff e8c9 2f3d f9a5 27bf f4e4 faee b85e fe1d 3a2b 7d83 5cfa 0eb9 f42d 72e9 7be4 7af9 5b1e 7fcf d36f 7afa 2eb9 f55d 72eb bbe4 d677 c9ad ef92 5bdf 25b7 be4b 6e7d 97dc fa2e b92f fe76 af27 d777 c97d e9c9 2f3d f9a5 2757 e4dc aa9c 5b99 73b3 7318 3a2e 1d3d f9ad 2767 eab0 7518 3bb7 9e9c b1a3 dab9 474f aeda b987 91a7 271f 3df9 e8c9 474f 3e7a f2d1 938f 9e3c 7af2 e8c9 a327 8f9e 3c7a f2e8 c9a3 278f 9e3c 7af2 e8c9 ab27 af9e bc7a f2ea c9cb b0d7 9357 4f5e 3d79 f5e4 d593 af9e 5c0d 7f2b e26f 55fc ad8c bf97 7fa6 d193 abe4 6fa5 fcad 96bf 15f3 b76a fe56 cedf eaf9 5b41 7fab e8ef c33f cee9 c915 f5b7 aafe 56d6 dfea fa5b 617f abec 6fa5 fdad b6bf 15f7 b7ea fe56 dedf eafb 5b81 7fab e16e 35dc ad86 bbd5 70b7 1aee 56c3 dd6a b8fb e51f e1f9 67f8 8f3e b8f4 c1ad 0f46 1f44 1f54 1fac 3e38 fae0 d107 7a72 35dc a8e1 460d 376a b851 c38d 1a6e d470 a386 1b35 dca8 e146 0d37 6ab8 51c3 8d1a 6ed4 70a3 861b 35dc a8e1 460d 37fc 7fac d470 a386 1b35 dca8 e146 0d37 6ab8 51c3 8d1a 6ed4 70a3 861b 35dc a8e1 460d 376a b851 c38d 1a6e d470 a386 1b35 dca8 e146 0d37 6ab8 51c3 8d1a 6ed4 70a3 861b 35dc a8e1 460d 376a b851 c38d 1a6e d470 a386 1b35 dca8 e146 0d37 6ab8 51c3 8d1a 6ed4 70a3 861b 35dc a8e1 460d 376a b851 c38d 1a6e d470 a386 1b35 dca8 e146 0d37 6ab8 51c3 8d1a 6ed4 70a3 861b 35dc a8e1 460d 376a b851 c38d 1a6";

//        zipFile = zipFile.replaceAll(" ","");

        String result = uncompressToString(zipFile.getBytes(), "UTF-8");
        System.out.println(result);
    }


    public static String uncompressToString(byte[] bytes, String encoding) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString(encoding);
        } catch (Exception e) {
            log.error("gzip uncompress error.", e);
        }
        return null;
    }

}
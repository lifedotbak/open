package com.spyker.framework.util.sign;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCrypt;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BCryptUtils {

    /**
     * 生成密码
     *
     * @param password
     * @return
     */
    public static String hashpw(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * 匹配密码
     *
     * @param password
     * @param hashed
     * @return
     */
    public static boolean checkpw(String password, String hashed) {

        return BCrypt.checkpw(password, hashed);
    }
}
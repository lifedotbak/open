package com.spyker.commons.utils;

import com.spyker.framework.util.BCryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class BCryptTest {

    @Test
    public void hashPw() {
        String password = BCryptUtils.hashpw("123456");
        log.info(password);

        boolean check = BCryptUtils.checkpw("123456", "$2a$10$An4XF1kI7SV4Ao.67K6Nke6/9i8z.OlzqLxLe6eSQYSrWfOCzrGjq");

        log.info(check + "");
    }
}
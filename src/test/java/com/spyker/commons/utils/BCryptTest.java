package com.spyker.commons.utils;

import com.spyker.framework.util.sign.BCryptUtils;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BCryptTest {

    @Test
    public void hashPw() {
        String password = BCryptUtils.hashpw("123456");
        log.info(password);

        boolean check =
                BCryptUtils.checkpw(
                        "123456", "$2a$10$An4XF1kI7SV4Ao.67K6Nke6/9i8z.OlzqLxLe6eSQYSrWfOCzrGjq");

        log.info(check + "");
    }

    @Test
    public void checkPw() {
        String s = "测试%s";
        String[] strings = {"id", "name"};

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        String xxx = String.format(s, list.toArray());
        log.info(xxx);
    }
}
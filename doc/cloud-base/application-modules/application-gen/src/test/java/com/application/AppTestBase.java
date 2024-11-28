package com.application;

import com.application.gen.GenApplication;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(
        classes = GenApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Rollback
@AutoConfigureMockMvc
@Slf4j
public class AppTestBase {

    @Test
    public void initAppTestBase() {
        log.info("initAppTestBase");
    }
}
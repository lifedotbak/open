package com.spyker;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(
        classes = OpenApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
// @Rollback(false)
@Rollback
public class BaseTest {}
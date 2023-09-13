package com.spyker.application;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.spyker.OpenApplication;

@SpringBootTest(classes = OpenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
//@Rollback(false)
@Rollback
public class BaseTest {

}
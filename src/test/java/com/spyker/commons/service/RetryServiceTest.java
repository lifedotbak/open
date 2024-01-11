package com.spyker.commons.service;

import com.spyker.BaseTest;
import com.spyker.commons.service.impl.RetryService;

import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class RetryServiceTest extends BaseTest {

    @Autowired private RetryService retryService;

    @Test
    public void retryService() {
        retryService.retryService();
    }

    @Test
    public void retryServiceWithRecovery() {
        retryService.retryServiceWithRecovery();
    }
}
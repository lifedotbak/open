package com.spyker.commons.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/** https://springdoc.cn/spring-retry-guide/ */
@Slf4j
@Service
public class RetryService {

    private int retryCount = 0;

    /**
     * 由于没有指定任何异常，因此将尝试重试所有异常。此外，一旦达到最大尝试次数， 但仍有异常，就会抛出 ExhaustedRetryException。
     *
     * <p>根据 @Retryable 的默认行为，重试最多可进行三次，两次重试之间的延迟时间为一秒。
     */
    @Retryable
    public void retryService() {

        log.info("retryCount-->{}", retryCount++);

        throw new RuntimeException("xxx");
    }

    /**
     * @Recover 注解定义了当 @Retryable 方法因指定异常而失败时的恢复方法。
     *
     * <p>因此，如果 retryServiceWithRecovery 方法在尝试三次后仍抛出 SqlException，就会调用 recover() 方法。
     *
     * <p>Recovery Handler 方法的第一个参数应为 Throwable（可选）类型，后续参数将按照失败方法的参数列表的相同顺序填充。且返回类型相同！
     */
    @Retryable(retryFor = RuntimeException.class)
    public void retryServiceWithRecovery() {
        log.info("retryCount-->{}", retryCount++);
        throw new RuntimeException("retryServiceWithRecovery");
    }

    /**
     * Retryable 方法对应的recover方法
     *
     * @param e
     * @param sql
     */
    @Recover
    public void recover(RuntimeException e, String sql) {
        log.info("e-->{}",e.getMessage());
        log.info("recover");
    }
}
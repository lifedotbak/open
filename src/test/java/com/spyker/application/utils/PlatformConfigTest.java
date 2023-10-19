package com.spyker.application.utils;

import com.spyker.application.BaseTest;
import com.spyker.framework.config.PlatformConfig;
import org.junit.jupiter.api.Test;

public class PlatformConfigTest extends BaseTest {

    @Test
    public void test() {
        System.out.println(PlatformConfig.getCaptchaEnabled());
        System.out.println(PlatformConfig.getCaptchaType());
    }

}
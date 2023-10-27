package com.spyker.commons.utils;

import com.spyker.BaseTest;
import com.spyker.commons.enums.ErrorCode;
import com.spyker.framework.config.PlatformConfig;
import org.junit.jupiter.api.Test;

public class PlatformConfigTest extends BaseTest {

    @Test
    public void test() {

        System.out.println(ErrorCode.UNKNOWN_ERROR.getCode());
        System.out.println(PlatformConfig.getCaptchaEnabled());
        System.out.println(PlatformConfig.getCaptchaType());
    }

}
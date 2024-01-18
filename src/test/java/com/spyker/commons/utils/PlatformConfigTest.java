package com.spyker.commons.utils;

import com.spyker.BaseTest;
import com.spyker.commons.enums.BusinessErrorCodeEnum;
import com.spyker.framework.properties.PlatformConfigProperties;
import org.junit.jupiter.api.Test;

public class PlatformConfigTest extends BaseTest {

    @Test
    public void test() {

        System.out.println(BusinessErrorCodeEnum.UNKNOWN_ERROR.getCode());
        System.out.println(PlatformConfigProperties.getCaptchaEnabled());
        System.out.println(PlatformConfigProperties.getCaptchaType());
    }
}
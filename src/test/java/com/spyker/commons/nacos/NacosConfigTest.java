package com.spyker.commons.nacos;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.spyker.BaseTest;

import org.junit.jupiter.api.Test;

public class NacosConfigTest extends BaseTest {

    /***
     *
     * nacos配置内容（Properties）
     * secretkey=100xxx
     * minx.secretkey=this is minx-->secretkey
     */

    @NacosValue(value = "${secretkey:default}", autoRefreshed = true)
    private String secretkey;

    @NacosValue(value = "${minx.secretkey:minx.default}", autoRefreshed = true)
    private String mixsecretkey;

    @Test
    public void testNacosConfig() {
        System.out.println(secretkey);
        System.out.println(mixsecretkey);
    }
}
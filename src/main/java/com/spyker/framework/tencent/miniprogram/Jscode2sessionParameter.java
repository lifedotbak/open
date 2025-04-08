package com.spyker.framework.tencent.miniprogram;

import lombok.Data;

@Data
public class Jscode2sessionParameter {

    private String appId;
    private String appSecret;
    private String jscode;
}

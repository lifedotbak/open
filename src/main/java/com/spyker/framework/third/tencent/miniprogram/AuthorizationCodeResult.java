package com.spyker.framework.third.tencent.miniprogram;

import lombok.Data;

@Data
public class AuthorizationCodeResult {

    private String openid;
    private String unionid;
    private String session_key;
    private String access_token;
    private String errcode;
    private String errmsg;
    private int expires_in;

}
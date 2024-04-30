package com.spyker.framework.onvif.ver10.entity;

import lombok.Data;

@Data
public class WsseUsernameToken {
    private String username;
    private String password;
    private String nonce;
    private String created;
    private String token;
}
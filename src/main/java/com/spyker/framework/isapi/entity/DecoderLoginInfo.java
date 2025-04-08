package com.spyker.framework.isapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DecoderLoginInfo {

    String ip;
    short port = 80;
    String userName;
    String password;
}

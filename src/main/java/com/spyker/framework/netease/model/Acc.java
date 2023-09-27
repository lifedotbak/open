package com.spyker.framework.netease.model;

import lombok.Data;

@Data
public class Acc {

    /**
     * 必填 网易云信IM账号，最大长度32字符，必须保证一个 APP内唯一（只允许字母、数字、半角下划线_、 @、半角点以及半角-组成，不区分大小写，
     * 会统一小写处理，请注意以此接口返回结果中的accid为准）。
     */
    private String accid;
    private String name;
    private String icon;
    private String token;
    private String sign;
    private String email;
    private String birth;
    private String mobile;
    private String gender;
    private String ex;

}
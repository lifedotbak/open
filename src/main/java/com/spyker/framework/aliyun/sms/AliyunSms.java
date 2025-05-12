package com.spyker.framework.aliyun.sms;

import com.alibaba.fastjson2.JSONObject;

import lombok.Data;

import java.util.Map;

/**
 * @author 121232224@qq.com
 */
@Data
public class AliyunSms {

    private String phoneNumber;

    private String templateCode;

    /** json 格式 "{\"code\":\"888888\"}" */
    // private String templateParam;
    private String outId;

    private Map<String, Object> paramMap;

    public String getTemplateParam() {

        String result = "";

        if (null != paramMap) {
            JSONObject jsonObject = new JSONObject(paramMap);

            result = jsonObject.toString();
        }

        return result;
    }
}

package com.spyker.framework.aliyun.push;

import com.spyker.framework.aliyun.push.enums.AndroidOpenType;
import com.spyker.framework.aliyun.push.enums.PushMessageType;
import com.spyker.framework.aliyun.push.enums.PushTargetType;

import lombok.Data;

import org.json.JSONObject;

import java.util.Map;

@Data
public class PushMessage {

    private String accessKeyId;
    private String accessKeySecret;
    private String signName;

    private long androidKey;
    private long iosKey;

    private PushTargetType pushTargetType = PushTargetType.ACCOUNT;
    private PushMessageType pushMessageType = PushMessageType.NOTICE;

    /** 接收推送的目标值，多个值使用逗号分隔.(帐号与设备有一次最多100个的限制) */
    private String targetValue;

    private String title;
    private String body;
    private String extParameters;

    private AndroidOpenType androidOpenType = AndroidOpenType.NONE;

    /** AndroidOpenType.URL */
    private String androidOpenUrl;

    /** AndroidOpenType.Activity */
    private String androidActivity;

    private String androidPopupActivity;
    private String androidPopupTitle;
    private String androidPopupBody;

    /** iOS通知副标题的内容 */
    private String iosSubtitle;

    /** 指定iOS10通知Category */
    private String iosNotificationCategory;

    /** iOS的通知是通过APNs中心来发送的，需要填写对应的环境信息。"DEV": 表示开发环境; "PRODUCT" : 表示生产环境 */
    private String iosApnsEnv;

    private Map<String, String> extParametersMap;

    public String getExtParameters() {

        if (null != extParametersMap) {
            JSONObject templateParamJsonObject = new JSONObject(extParametersMap);

            extParameters = templateParamJsonObject.toString();
        }

        return extParameters;
    }
}

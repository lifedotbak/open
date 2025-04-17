package com.spyker.framework.aliyun.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

@Slf4j
@AutoConfiguration
@ConditionalOnClass(AliyunSmsProperties.class)
public final class AliyunSendSmsUtils {

    @Autowired AliyunSmsProperties aliyunSmsProperties;

    public void sendSms(AliyunSms sms) {

        DefaultProfile profile =
                DefaultProfile.getProfile(
                        "cn-hangzhou",
                        aliyunSmsProperties.getAccessKeyId(),
                        aliyunSmsProperties.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("SignName", aliyunSmsProperties.getSignName());
        request.putQueryParameter("RegionId", "cn-hangzhou");

        request.putQueryParameter("PhoneNumbers", sms.getPhonenumber());
        request.putQueryParameter("TemplateCode", sms.getTempleteCode());
        request.putQueryParameter("TemplateParam", sms.getTemplateParam());

        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info(response.getData());
        } catch (ClientException e) {
            log.error("Failed：");
            log.error("Error code: " + e.getErrMsg());
            log.error("Error message: " + e.getErrCode());
            log.error("RequestId: " + e.getRequestId());
            log.error(e.getMessage());
        }
    }
}

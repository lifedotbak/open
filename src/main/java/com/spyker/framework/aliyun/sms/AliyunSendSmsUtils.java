package com.spyker.framework.aliyun.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.spyker.framework.aliyun.AliyunConfigProperties;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

@Slf4j
@AutoConfiguration
@ConditionalOnClass(AliyunConfigProperties.class)
public final class AliyunSendSmsUtils {

    @Autowired AliyunConfigProperties aliyunConfigProperties;

    public void sendSms(AliyunSms sms) {

        AliyunConfigProperties.Sms smsProperties = aliyunConfigProperties.getSms();

        DefaultProfile profile =
                DefaultProfile.getProfile(
                        "cn-hangzhou",
                        smsProperties.getAccessKeyId(),
                        smsProperties.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("SignName", smsProperties.getSignName());
        request.putQueryParameter("RegionId", "cn-hangzhou");

        request.putQueryParameter("PhoneNumbers", sms.getPhonenumber());
        request.putQueryParameter("TemplateCode", sms.getTempleteCode());
        request.putQueryParameter("TemplateParam", sms.getTemplateParam());

        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info(response.getData());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}

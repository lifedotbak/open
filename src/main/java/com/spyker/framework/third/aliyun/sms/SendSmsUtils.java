package com.spyker.framework.third.aliyun.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SendSmsUtils {

    public static void sendSms(Sms sms) {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", sms.getAccessKeyId(), sms.getAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", sms.getPhonenumber());
        request.putQueryParameter("SignName", sms.getSignName());
        request.putQueryParameter("TemplateCode", sms.getTempleteCode());
        request.putQueryParameter("TemplateParam", sms.getTemplateParam());

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());

            log.info(response.getData());

        } catch (ServerException e) {
            e.printStackTrace();

            log.error(e.getErrMsg());
        } catch (ClientException e) {
            log.error(e.getErrMsg());
        }
    }

}
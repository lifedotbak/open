package com.spyker.framework.aliyun.voice;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.spyker.framework.aliyun.AliyunConfigProperties;

import lombok.extern.slf4j.Slf4j;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@AutoConfiguration
@ConditionalOnClass(AliyunConfigProperties.class)
public class SingleCallByTtsUtils {

    @Autowired AliyunConfigProperties aliyunConfigProperties;

    /**
     * 您已接单，请尽快到达${address}
     *
     * @param calledNumber
     * @param address
     */
    public void TTS_197465247(String calledNumber, String address) {

        AliyunConfigProperties.Tts tts = aliyunConfigProperties.getTts();

        DefaultProfile profile =
                DefaultProfile.getProfile(
                        "default", tts.getAccessKeyId(), tts.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dyvmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SingleCallByTts");
        // request.putQueryParameter("CalledShowNumber", "02985796072");
        request.putQueryParameter("CalledNumber", calledNumber);
        request.putQueryParameter("TtsCode", "TTS_197465247");

        Map<String, String> ttsParam = new HashMap<>();
        ttsParam.put("address", address);

        JSONObject jsonObject = new JSONObject(ttsParam);
        //		JSONObject jsonObject = JSONObject.fromObject(ttsParam);

        request.putQueryParameter("TtsParam", jsonObject.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            log.info("TTS_197465247 response:{}", response.getData());
        } catch (Exception e) {
            log.error("TTS_197465247 exception:", e);
        }
    }

    /**
     * ${washItem}订单离支付失效还有5分钟，请尽快支付预付款
     *
     * @param calledNumber
     * @param washItem
     */
    public void TTS_197595183(String calledNumber, String washItem) {

        AliyunConfigProperties.Tts tts = aliyunConfigProperties.getTts();

        DefaultProfile profile =
                DefaultProfile.getProfile(
                        "default", tts.getAccessKeyId(), tts.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dyvmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SingleCallByTts");
        // request.putQueryParameter("CalledShowNumber", "02985796072");
        request.putQueryParameter("CalledNumber", calledNumber);
        request.putQueryParameter("TtsCode", "TTS_197595183");

        Map<String, String> ttsParam = new HashMap<>();
        ttsParam.put("washItem", washItem);

        JSONObject jsonObject = new JSONObject(ttsParam);
        //		JSONObject jsonObject = JSONObject.fromObject(ttsParam);

        request.putQueryParameter("TtsParam", jsonObject.toString());

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (Exception e) {
            log.error("TTS_197595183 exception:", e);
        }
    }

    public void singleCallByTts(String calledNumber, String ttsParam, String ttsCode) {

        AliyunConfigProperties.Tts tts = aliyunConfigProperties.getTts();

        DefaultProfile profile =
                DefaultProfile.getProfile(
                        "default", tts.getAccessKeyId(), tts.getAccessKeySecret());

        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dyvmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SingleCallByTts");
        // request.putQueryParameter("CalledShowNumber", "02985796072");
        request.putQueryParameter("CalledNumber", calledNumber);
        request.putQueryParameter("TtsCode", ttsCode);
        request.putQueryParameter("TtsParam", ttsParam);
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info(response.getData());
        } catch (Exception e) {
            log.error(ttsCode + " exception:", e);
        }
    }
}

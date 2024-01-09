package com.spyker.framework.third.aliyun.voice;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
// @Component
public class SingleCallByTtsUtils {

  @Value("${aliyun.accessKeyId}")
  private String accesskeyid;

  @Value("${aliyun.accessKeySecret}")
  private String accesskeysecret;

  /**
   * ${washItem}订单离支付失效还有5分钟，请尽快支付预付款
   *
   * @param calledNumber
   * @param washItem
   */
  public static void TTS_197595183(String calledNumber, String washItem) {

    DefaultProfile profile = DefaultProfile.getProfile("default", "", "");
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

  /**
   * 您已接单，请尽快到达${address}
   *
   * @param calledNumber
   * @param address
   */
  public static void TTS_197465247(String calledNumber, String address) {

    DefaultProfile profile = DefaultProfile.getProfile("default", "", "");
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
    } catch (Exception e) {
      log.error("TTS_197465247 exception:", e);
    }
  }

  public void singleCallByTts(String calledNumber, String ttsParam, String ttsCode) {

    DefaultProfile profile = DefaultProfile.getProfile("default", accesskeyid, accesskeysecret);
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
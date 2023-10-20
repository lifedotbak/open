package com.spyker.framework.third.tencent.miniprogram;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Slf4j
public class AuthCode2SessionUtils {

    private AuthCode2SessionUtils() {

    }

    public static AuthorizationCodeResult code2Session(Jscode2sessionParameter jscode2sessionParameter) {

        AuthorizationCodeResult result = new AuthorizationCodeResult();

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + jscode2sessionParameter.getAppId() +
                "&secret=" + jscode2sessionParameter.getAppSecret() + "&js_code=" + jscode2sessionParameter.getJscode() + "&grant_type=authorization_code";

        //		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxadec7f25753bcc26&secret=60d00f7edaad7c745a27f3e6a9f32693&js_code="
        //				+ jscode + "&grant_type=authorization_code";
        String resultValue = "";

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            HttpGet httpGet = new HttpGet(url);

            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                HttpEntity entity = response.getEntity();

                resultValue = EntityUtils.toString(entity, "UTF-8");

                log.info("resultValue --> {}", resultValue);

                Gson gson = new Gson();

                result = gson.fromJson(resultValue, AuthorizationCodeResult.class);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            log.info("error --> {}", e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                log.info("error --> {}", e);
            }
        }

        return result;
    }
}
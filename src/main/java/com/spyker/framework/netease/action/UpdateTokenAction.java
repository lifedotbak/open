package com.spyker.framework.netease.action;

import com.spyker.framework.netease.utils.CheckSumBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 更新网易云信IM token
 * <p>
 * <p>
 * 更新网易云信IM token。通过该接口，可以对accid更新到指定的IM
 * token，更新后请开发者务必做好本地的维护。更新后，需要确保客户端SDK再次登录时携带的token保持最新。
 *
 * @author spyker
 */
public class UpdateTokenAction {

    private static final String url = "https://api.netease.im/nimserver/user/update.action";

    private final String appKey;
    private final String appSecret;

    public UpdateTokenAction(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public void main() throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(url);

        String nonce = "12345";
        String curTime = String.valueOf(new Date().getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);// 参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("accid", "helloworld"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);

        // 打印执行结果
        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
    }

}
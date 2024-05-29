package com.spyker.framework.netease.action;

import com.google.gson.Gson;
import com.spyker.framework.netease.model.AccCreateResult;
import com.spyker.framework.netease.utils.CheckSumBuilder;
import com.spyker.framework.util.ExRandomStringUtils;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 创建网易云信IM账号
 *
 * <p>网易云信IM账号：以下文档中也称为“用户帐号”，参数名用“accid”或”account“等表示。
 *
 * <p>token：网易云信IM账号的密码。创建 IM accid时可以由开发者app的服务端指定。若未指定，则云信会自动生成一个IM
 * token，并返回给开发者。客户端登录时，需要传参accid与token给云信服务器鉴权。 token没有过期的概念，除非人为更改。只有最新的token才是唯一有效的。
 * 当登录时使用非最新的token，将会返回的错误码 302。
 *
 * <p>
 *
 * <p>客户端通过网易云信SDK连接登录云信服务器时，需要保证网易云信IM账号已经注册， 且确保客户端从自己的服务器已经取得了有效token；
 *
 * @author spyker
 */
@Slf4j
public class UserCreateAction {

    private static final String url = "https://api.netease.im/nimserver/user/create.action";

    private final String appKey;
    private final String appSecret;

    public UserCreateAction(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public String execute(String accId) {

        String result = "";

        try {

            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpPost httpPost = new HttpPost(url);

            String nonce = ExRandomStringUtils.random32();
            String curTime = String.valueOf(new Date().getTime() / 1000L);
            String checkSum =
                    CheckSumBuilder.getCheckSum(appSecret, nonce, curTime); // 参考 计算CheckSum的java代码

            // 设置请求的header
            httpPost.addHeader("AppKey", appKey);
            httpPost.addHeader("Nonce", nonce);
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", checkSum);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // 设置请求的参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("accid", accId));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity httpEntity = response.getEntity();

            String httpResult = EntityUtils.toString(httpEntity, "UTF-8");

            log.info(httpResult);

            Gson gson = new Gson();

            AccCreateResult accCreateResult = gson.fromJson(httpResult, AccCreateResult.class);

            // 打印执行结果
            log.info("accCreateResult --> {}", accCreateResult);

            if (null != accCreateResult && 200 == accCreateResult.getCode()) {
                result = accCreateResult.getInfo().getToken();
            }

        } catch (Exception e) {
            log.error("create netease user exception --> {}", e);
        }

        return result;
    }
}
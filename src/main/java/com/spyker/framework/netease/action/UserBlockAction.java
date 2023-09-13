package com.spyker.framework.netease.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.spyker.framework.netease.utils.CheckSumBuilder;

/**
 * 封禁网易云信IM账号
 * 
 * 
 * 1.封禁网易云信IM账号后，此ID将不能再次登录。若封禁时，该id处于登录状态，则当前登录不受影响，仍然可以收发消息。封禁效果会在下次登录时生效。因此建议，将needkick设置为true，让该账号同时被踢出登录。
 * 
 * 2.出于安全目的，账号创建后只能封禁，不能删除；封禁后账号仍计入应用内账号总数。
 * 
 * @author spyker
 *
 */
public class UserBlockAction {

	private static final String url = "https://api.netease.im/nimserver/user/block.action";

	private String appKey;
	private String appSecret;

	public UserBlockAction(String appKey, String appSecret) {
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

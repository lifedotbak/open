package com.spyker.framework.tencent.pay.v3.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import lombok.SneakyThrows;

public final class PrivateKeyUtil {

	/**
	 * 获取私钥。
	 *
	 * @param filename 私钥文件路径 (required) apiclient_key.pem
	 * @return 私钥对象
	 */
	@SneakyThrows
	public static PrivateKey getPrivateKey(String filename) {

		String content = new String(Files.readAllBytes(Paths.get(filename)), "utf-8");
		try {
			String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
					.replace("-----END PRIVATE KEY-----", "").replaceAll("\\s+", "");

			KeyFactory kf = KeyFactory.getInstance("RSA");
			return kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("当前Java环境不支持RSA", e);
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException("无效的密钥格式");
		}
	}

}

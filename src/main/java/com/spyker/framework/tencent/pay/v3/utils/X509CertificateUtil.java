package com.spyker.framework.tencent.pay.v3.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;

/**
 * 获取证书。
 *
 * https://wechatpay-api.gitbook.io/wechatpay-api-v3/chang-jian-wen-ti/zheng-shu-xiang-guan
 *
 * @author zhangzhaofeng
 *
 */
public final class X509CertificateUtil {

	/**
	 * 获取证书。
	 *
	 * @param filename 证书文件路径 (required) apiclient_cert.pem文件
	 * @return X509证书
	 */
	public static X509Certificate getCertificate(String filename) throws IOException {
		InputStream fis = new FileInputStream(filename);
		BufferedInputStream bis = new BufferedInputStream(fis);

		try {
			CertificateFactory cf = CertificateFactory.getInstance("X509");
			X509Certificate cert = (X509Certificate) cf.generateCertificate(bis);
			cert.checkValidity();
			return cert;
		} catch (CertificateExpiredException e) {
			throw new RuntimeException("证书已过期", e);
		} catch (CertificateNotYetValidException e) {
			throw new RuntimeException("证书尚未生效", e);
		} catch (CertificateException e) {
			throw new RuntimeException("无效的证书文件", e);
		} finally {
			bis.close();
		}
	}
}

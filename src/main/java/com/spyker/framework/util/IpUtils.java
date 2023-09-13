package com.spyker.framework.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class IpUtils {

	private static Log log = LogFactory.getLog(IpUtils.class);

	public static final String getHostIpAddress() {

		String result = "";

		try {
			result = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.error(e);
		}

		return result;
	}

}

package com.wmedya.payu.client.util;

import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

public class HashUtils {

	private static Logger logger = Logger.getLogger(HashUtils.class);

	public static String calculateHash(String secret, Map<String, String> params) {
		StringBuilder sb = new StringBuilder();
		for (String key : params.keySet()) {
			Object value = params.get(key);
			if (value != null && value instanceof String) {
				String valueString = (String) value;
				valueString = StringUtils.sanitizeString(valueString);
				sb.append((valueString).length()).append(valueString);
			}
		}

		String parametersString = sb.toString();
		logger.debug("joining paramteres...");
		logger.debug(parametersString);

		String orderHash = hmacMD5(parametersString, secret);
		logger.debug("calculating order_hash...");
		logger.debug(orderHash);
		return orderHash;
	}

	private static String hmacMD5(String msg, String secretKey) {
		String digest = null;
		try {
			SecretKeySpec key = new SecretKeySpec((secretKey).getBytes("UTF-8"), "HmacMD5");
			Mac mac = Mac.getInstance("HmacMD5");
			mac.init(key);

			byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

			StringBuffer hash = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				String hex = Integer.toHexString(0xFF & bytes[i]);
				if (hex.length() == 1) {
					hash.append('0');
				}
				hash.append(hex);
			}
			digest = hash.toString();
		} catch (Exception e) {
			logger.error(e);
		}
		return digest;
	}

}

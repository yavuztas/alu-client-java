package com.wmedya.payu.client.util;

import java.text.Normalizer;

public class StringUtils {

	public static String sanitizeString(String text) {
		text = Normalizer.normalize(text, Normalizer.Form.NFD);
		return text.replaceAll("[^\\x00-\\x7F]", "");
	}

}

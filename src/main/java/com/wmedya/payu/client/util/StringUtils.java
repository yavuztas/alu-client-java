package com.wmedya.payu.client.util;

import java.text.Normalizer;

public class StringUtils {

	public static String sanitizeString(String text) {
		text = Normalizer.normalize(text, Normalizer.Form.NFD);
		return text.replaceAll("[^\\x00-\\x7F]", "");

		/*
		 * String[] chars = new String[] { "Ö", "ö", "Ş", "ş", "Ğ", "ğ", "Ü",
		 * "ü", "İ", "ı", "Ç", "ç" }; String[] convert = new String[] { "O",
		 * "o", "S", "s", "G", "g", "U", "u", "I", "i", "C", "c" };
		 * 
		 * StringBuilder sb = new StringBuilder(); List<String> list1 =
		 * Arrays.asList(chars); List<String> list2 = Arrays.asList(convert);
		 * for (int i = 0; i < text.length(); i++) { char charAt =
		 * text.charAt(i); String character = String.valueOf(charAt); if
		 * (list1.contains(character)) { character =
		 * list2.get(list1.indexOf(character)); } sb.append(character); } return
		 * sb.toString();
		 */
	}

	public static void main(String[] args) {

		String text = "ä";
		String sanitizeString = StringUtils.sanitizeString(text);
		System.out.println(sanitizeString);

	}
}

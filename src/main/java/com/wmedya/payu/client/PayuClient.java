package com.wmedya.payu.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.wmedya.payu.client.model.Billing;
import com.wmedya.payu.client.model.Card;
import com.wmedya.payu.client.model.Delivery;
import com.wmedya.payu.client.model.MerchantConfig;
import com.wmedya.payu.client.model.MerchantPlatform;
import com.wmedya.payu.client.model.Order;
import com.wmedya.payu.client.model.Product;
import com.wmedya.payu.client.model.User;

public class PayuClient implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(PayuClient.class);

	private MerchantConfig config;

	private Map<MerchantPlatform, String> platformUrls = new HashMap<MerchantPlatform, String>();

	private HttpClient client = new DefaultHttpClient();

	private ObjectMapper objectMapper = new ObjectMapper();
	private XmlMapper xmlMapper = new XmlMapper();

	private Order order;
	private Card card;
	private User user;

	public PayuClient(MerchantConfig config) {
		this.config = config;

		platformUrls.put(MerchantPlatform.TR, "https://secure.payu.com.tr/order/alu/v3");
		platformUrls.put(MerchantPlatform.HU, "https://secure.payu.hu/order/alu/v3");
		platformUrls.put(MerchantPlatform.RO, "https://secure.payu.ro/order/alu/v3");
		platformUrls.put(MerchantPlatform.RU, "https://secure.payu.ru/order/alu/v3");
		platformUrls.put(MerchantPlatform.UA, "https://secure.payu.ua/order/alu/v3");
	}

	public PayuResponse pay() {
		if (order == null) {
			throw new RuntimeException("order must not be null");
		}
		if (card == null) {
			throw new RuntimeException("card must not be null");
		}
		if (user == null) {
			throw new RuntimeException("user must not be null");
		}
		return pay(order, card, user);
	}

	public PayuResponse pay(Order order, Card card, User user) {

		this.order = order;
		this.card = card;
		this.user = user;

		String url = platformUrls.get(config.getPlatform());

		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");

		Map<String, String> params = getParams();

		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		for (String key : params.keySet()) {
			postParams.add(new BasicNameValuePair(key, params.get(key)));
		}

		PayuResponse payuResponse = null;
		try {
			logger.debug("executing http post request...");
			post.setEntity(new UrlEncodedFormEntity(postParams));
			HttpResponse response = client.execute(post);
			logger.debug("Response Code : " + response.getStatusLine().getStatusCode());
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.debug("Response as string:");
			logger.debug(result);
			payuResponse = xmlMapper.readValue(result, PayuResponse.class);

		} catch (Exception e) {
			logger.error(e);
		}

		return payuResponse;
	}

	public Map<String, String> getParams() {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("MERCHANT", config.getCode());

		LinkedHashMap map = null;
		if (order != null) {
			map = objectMapper.convertValue(order, LinkedHashMap.class);
			for (Object key : map.keySet()) {
				Object value = map.get(key);
				if (value != null) {
					params.put((String) key, convertToString(value));
				}
			}

			for (Product product : order.getProducts()) {
				map = objectMapper.convertValue(product, LinkedHashMap.class);
				for (Object key : map.keySet()) {
					Object value = map.get(key);
					if (value != null) {
						String valueString = convertToString(value);
						String keyString = addIndexToKey((String) key, order.getProducts().indexOf(product));
						params.put(keyString, valueString);
					}
				}
			}

			Billing billing = order.getBilling();
			if (billing != null) {
				map = objectMapper.convertValue(billing, LinkedHashMap.class);
				for (Object key : map.keySet()) {
					Object value = map.get(key);
					if (value != null) {
						String valueString = convertToString(value);
						params.put((String) key, valueString);
					}
				}
			}

			Delivery delivery = order.getDelivery();
			if (delivery != null) {
				map = objectMapper.convertValue(delivery, LinkedHashMap.class);
				for (Object key : map.keySet()) {
					Object value = map.get(key);
					if (value != null) {
						String valueString = convertToString(value);
						params.put((String) key, valueString);
					}
				}
			}
		}

		if (card != null) {
			map = objectMapper.convertValue(card, LinkedHashMap.class);
			for (Object key : map.keySet()) {
				Object value = map.get(key);
				if (value != null) {
					String valueString = convertToString(value);
					params.put((String) key, valueString);
				}
			}
		}

		if (user != null) {
			map = objectMapper.convertValue(user, LinkedHashMap.class);
			for (Object key : map.keySet()) {
				Object value = map.get(key);
				if (value != null) {
					String valueString = convertToString(value);
					params.put((String) key, valueString);
				}
			}
		}

		Map<String, String> copyParams = new LinkedHashMap<>();
		copyParams.putAll(params);
		copyParams.put("ORDER_HASH", calculateHash(params));

		logger.debug("total parameters...");
		logger.debug(copyParams);

		return copyParams;
	}

	private String calculateHash(Map<String, String> params) {
		StringBuilder sb = new StringBuilder();
		for (String key : params.keySet()) {
			Object value = params.get(key);
			if (value != null && value instanceof String) {
				sb.append(((String) value).length()).append(value);
			}
		}

		String parametersString = sb.toString();
		logger.debug("joining paramteres...");
		logger.debug(parametersString);

		String orderHash = hmacMD5(parametersString, config.getSecret());
		logger.debug("calculating order_hash...");
		logger.debug(orderHash);
		return orderHash;
	}

	/**
	 * Checks the response coming from Payu is valid
	 * 
	 * @param payuResponse
	 * @return
	 */
	public boolean checkResponseHash(PayuResponse payuResponse) {
		LinkedHashMap checkMap = objectMapper.convertValue(payuResponse, LinkedHashMap.class);
		String hash = (String) checkMap.get("HASH");
		if (hash != null) {
			checkMap.remove("URL_3DS");
			checkMap.remove("HASH");
			logger.debug("Response Hash Check:");
			logger.debug(checkMap);
			String calculatedHash = calculateHash(checkMap);
			return calculatedHash.contentEquals(hash);
		}
		return false;
	}

	private String addIndexToKey(String key, int index) {
		String indexedKey = key;
		if (key.endsWith("[]")) {
			indexedKey = key.replace("[]", "[" + index + "]");
		}
		return indexedKey;
	}

	private String convertToString(Object value) {
		Object cast;
		try {
			cast = (Number) value;
		} catch (Exception e) {
			cast = (String) value;
		}
		return cast.toString();
	}

	private String hmacMD5(String msg, String secretKey) {
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

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

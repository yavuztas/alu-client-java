package com.wmedya.payu.client;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmedya.payu.client.model.MerchantConfig;
import com.wmedya.payu.client.util.HashUtils;

public class IpnRequestBuilder implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(IpnRequestBuilder.class);

	private ObjectMapper mapper = new ObjectMapper();

	private MerchantConfig config;

	public IpnRequestBuilder(MerchantConfig config) {
		this.config = config;
	}

	public IpnRequest getIpnRequest(HttpServletRequest request) {
		return getIpnRequest(request, false);
	}

	public IpnRequest getIpnRequest(HttpServletRequest request, boolean validate) {
		Map<String, String> parameterMap = new TreeMap<>();
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name = (String) parameterNames.nextElement();
			parameterMap.put(name, request.getParameter(name));
		}

		if (validate) {
			if (!checkRequestHash(parameterMap)) {
				throw new RuntimeException("IPN Request sender could not be validated!");
			}
		}
		return mapper.convertValue(parameterMap, IpnRequest.class);
	}

	public boolean checkRequestHash(Map<String, String> params) {
		String hash = (String) params.get("HASH");
		if (hash != null && hash.trim().length() > 0) {
			params.remove("HASH");
			logger.debug("Response Hash Check:");
			logger.debug(params);
			String calculatedHash = HashUtils.calculateHash(config.getSecret(), params);
			System.out.println(hash);
			System.out.println(calculatedHash);
			return calculatedHash.contentEquals(hash);
		}
		return false;
	}

	public String buildResponse(IpnRequest ipnRequest) {
		Map<String, String> map = new HashMap<>();
		map.put("IPN_PID", ipnRequest.getIpnPid());
		map.put("IPN_PNAME", ipnRequest.getIpnPName());
		map.put("IPN_DATE", ipnRequest.getIpnDate());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = dateFormat.format(new Date());
		map.put("DATE", date);
		String hash = HashUtils.calculateHash(config.getSecret(), map);
		return "<EPAYMENT>" + date + "|" + hash + "</EPAYMENT>";
	}

	public static void main(String[] args) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String format = dateFormat.format(new Date());
		System.out.println(format);

	}

}

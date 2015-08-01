package com.wmedya.payu.client;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
		return getIpnRequest(request, false, null);
	}

	public IpnRequest getIpnRequest(HttpServletRequest request, boolean validate) {
		return getIpnRequest(request, validate, null);
	}

	public IpnRequest getIpnRequest(HttpServletRequest request, boolean validate, String[] validateOrder) {
		Map<String, String> parameters = new LinkedHashMap<>();

		if (validateOrder != null && validateOrder.length > 0) {
			for (String key : validateOrder) {
				parameters.put(key, request.getParameter(key));
			}
			parameters.put("HASH", request.getParameter("HASH"));
		} else {
			Enumeration parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String name = (String) parameterNames.nextElement();
				parameters.put(name, request.getParameter(name));
			}
		}

		if (validate) {
			if (!checkRequestHash(parameters)) {
				throw new RuntimeException("IPN Request sender could not be validated!");
			}
		}
		return mapper.convertValue(parameters, IpnRequest.class);
	}

	public boolean checkRequestHash(Map<String, String> params) {
		String hash = (String) params.get("HASH");
		if (hash != null && hash.trim().length() > 0) {
			params.remove("HASH");
			logger.debug("Response Hash Check:");
			logger.debug(params);
			String calculatedHash = HashUtils.calculateHash(config.getSecret(), params);
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

	private Map<String, String> getDefaultIpnRequestHashParameters() {

		Map<String, String> params = new LinkedHashMap<>();
		params.put("SALEDATE", "");
		params.put("REFNO", "");
		params.put("REFNOEXT", "");
		params.put("ORDERNO", "");
		params.put("ORDERSTATUS", "");
		params.put("PAYMETHOD", "");
		params.put("FIRSTNAME", "");
		params.put("LASTNAME", "");
		params.put("IDENTITY_NO", "");
		params.put("IDENTITY_ISSUER", "");
		params.put("COMPANY", "");
		params.put("REGISTRATIONNUMBER", "");
		params.put("FISCALCODE", "");
		params.put("CBANKNAME", "");
		params.put("CBANKACCOUNT", "");
		params.put("ADDRESS1", "");
		params.put("ADDRESS2", "");
		params.put("CITY", "");
		params.put("STATE", "");
		params.put("ZIPCODE", "");
		params.put("COUNTRY", "");
		params.put("PHONE", "");
		params.put("FAX", "");
		params.put("CUSTOMEREMAIL", "");
		params.put("FIRSTNAME_D", "");
		params.put("LASTNAME_D", "");
		params.put("COMPANY_D", "");
		params.put("ADDRESS1_D", "");
		params.put("ADDRESS2_D", "");
		params.put("CITY_D", "");
		params.put("STATE_D", "");
		params.put("ZIPCODE_D", "");
		params.put("COUNTRY_D", "");
		params.put("PHONE_D", "");
		params.put("IPADDRESS", "");
		params.put("CURRENCY", "");
		params.put("IPN_PID[]", "");
		params.put("IPN_PNAME[]", "");
		params.put("IPN_PCODE[]", "");
		params.put("IPN_INFO[]", "");
		params.put("IPN_QTY[]", "");
		params.put("IPN_PRICE[]", "");
		params.put("IPN_VAT[]", "");
		params.put("IPN_VER[]", "");
		params.put("IPN_DISCOUNT[]", "");
		params.put("IPN_PROMONAME[]", "");
		params.put("IPN_DELIVEREDCODES[]", "");
		params.put("IPN_TOTAL[]", "");
		params.put("IPN_TOTALGENERAL", "");
		params.put("IPN_SHIPPING", "");
		params.put("IPN_COMMISSION", "");
		params.put("IPN_DATE", "");

		return params;
	}

}

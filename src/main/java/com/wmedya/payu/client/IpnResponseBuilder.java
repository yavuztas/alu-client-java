package com.wmedya.payu.client;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class IpnResponseBuilder implements Serializable {

	private static final long serialVersionUID = 1L;

	private ObjectMapper mapper = new ObjectMapper();

	public IpnResponseBuilder() {

	}

	public IpnResponse getResponse(HttpServletRequest request) {
		Map params = request.getParameterMap();
		IpnResponse response = mapper.convertValue(params, IpnResponse.class);
		return response;
	}

}

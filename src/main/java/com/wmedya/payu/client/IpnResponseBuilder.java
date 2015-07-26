package com.wmedya.payu.client;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class IpnResponseBuilder implements Serializable {

	private static final long serialVersionUID = 1L;

	private ObjectMapper mapper = new ObjectMapper();

	public IpnResponseBuilder() {

	}

	public IpnResponse getResponse(HttpServletRequest request) {
		HashMap<String, Object> parameterMap = new HashMap<>();
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name = (String) parameterNames.nextElement();
			parameterMap.put(name, request.getParameter(name));
		}
		IpnResponse response = mapper.convertValue(parameterMap, IpnResponse.class);
		return response;
	}

}

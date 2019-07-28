package com.wmedya.payu.client.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User class that specifies the shopper with details
 * <p>
 * <b>Usage</b>
 * 
 * <pre>
 * User user = new User(&quot;127.0.0.1&quot;, &quot;2015-05-27 07:13;54&quot;);
 * </pre>
 * 
 * @author Yavuz Tas
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("CLIENT_IP")
	private String clientIp;

	@JsonProperty("CLIENT_TIME")
	private String clientTime;

	/**
	 * Creates a user represents the shopper with details
	 */
	public User() {
	}

	/**
	 * Creates a user represents the shopper with details
	 * 
	 * @param clientIp IP address of the Shopper
	 */
	public User(String clientIp) {
		this(clientIp, null);
	}

	/**
	 * Creates a user represents the shopper with details
	 * 
	 * @param clientIp   IP address of the Shopper
	 * @param clientTime Time collected from the Shopper's browser in YYYY-MM-DD
	 *                   hh:mm:ss format
	 */
	public User(String clientIp, String clientTime) {
		this.clientIp = clientIp;
		this.clientTime = clientTime;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getClientTime() {
		return clientTime;
	}

	public void setClientTime(String clientTime) {
		this.clientTime = clientTime;
	}

}

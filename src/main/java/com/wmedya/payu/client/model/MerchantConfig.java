package com.wmedya.payu.client.model;

import java.io.Serializable;

/**
 * Configuration class that specifies a configuration for the merchant account.
 * <p>
 * <b>Usage</b>
 * 
 * <pre>
 * MerchantConfig config = new MerchantConfig(&quot;YOUR_MERCHANT_CODE&quot;, &quot;YOUR_SECRET_KEY&quot;, MerchantPlatform.TR);
 * </pre>
 * 
 * @author Yusuf Ziya Tekin
 *
 */
public class MerchantConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String code;
	private final String secret;
	private final MerchantPlatform platform;

	/**
	 * Creates a configuration for a merchant account.
	 * 
	 * @param code
	 *            Your PayU Merchant Code
	 * @param secret
	 *            Your PayU Secret Key
	 * @param platform
	 *            see {@link MerchantPlatform}
	 */
	public MerchantConfig(String code, String secret, MerchantPlatform platform) {
		this.code = code;
		this.secret = secret;
		this.platform = platform;
	}

	public String getCode() {
		return code;
	}

	public String getSecret() {
		return secret;
	}

	public MerchantPlatform getPlatform() {
		return platform;
	}

}

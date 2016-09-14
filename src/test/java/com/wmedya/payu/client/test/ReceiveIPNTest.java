package com.wmedya.payu.client.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.wmedya.payu.client.IpnRequest;
import com.wmedya.payu.client.IpnRequestBuilder;
import com.wmedya.payu.client.model.IpnOrderStatus;
import com.wmedya.payu.client.model.MerchantConfig;
import com.wmedya.payu.client.model.MerchantPlatform;

public class ReceiveIPNTest {

	private Logger logger = Logger.getLogger(ReceiveIPNTest.class);

	private MerchantConfig config;

	/**
	 * Special builder class for generating ipn response to payu to validate ipn
	 * requests
	 */
	private IpnRequestBuilder ipnResponseBuilder;

	@Before
	public void setUp() {
		config = new MerchantConfig("YOUR_CODE", "YOUR_SECRET", MerchantPlatform.TR);
		ipnResponseBuilder = new IpnRequestBuilder(config);
	}

	@Test
	public void receiveAndValidateIpnRequest() {

		// Assume that this is your real ipn request coming from servlet. I am
		// really lazy to mocking this right now :)
		HttpServletRequest request = null;

		// this order should be changed according to your payu ipn
		// configuration. Just debug ipn from payu panel and see what fields are
		// sent by payu. In order to validate ipn request coming from payu you
		// should write these field names as the same order coming from http
		// request
		String[] order = new String[] { "SALEDATE", "PAYMENTDATE", "REFNO", "REFNOEXT", "ORDERNO", "ORDERSTATUS", "COMPLETE_DATE", "IPN_PID[]",
				"IPN_PNAME[]", "IPN_DATE", "IPN_CC_TOKEN", "IPN_CC_MASK", "IPN_CC_EXP_DATE" };

		// second parameter is hash validation
		IpnRequest ipnResponse = ipnResponseBuilder.getIpnRequest(request, true, order);
		// or you can omit hash validation if you do not have a security
		// concern. In case you do not need the field order
		ipnResponse = ipnResponseBuilder.getIpnRequest(request, false);

		// this is the reference id you send before
		String externalRef = ipnResponse.getRefNoExternal();

		// check the payment is successfull
		if (ipnResponse.isSuccessfullyPaid()) {
			// you can take token and card mask if you marked the first payment
			// as token payment. See: TokenPaymentTest.java
			String ipnCardToken = ipnResponse.getIpnCardToken();
			String ipnCardMask = ipnResponse.getIpnCardMask();
			// do other updates specific your bussiness
			logger.info("payment is successfull");
		} else if (ipnResponse.getOrderStatus().equals(IpnOrderStatus.REVERSED)) {
			// do other updates specific your bussiness
			logger.info("payment is reversed");
		} else if (ipnResponse.getOrderStatus().equals(IpnOrderStatus.REFUND)) {
			// do other updates specific your bussiness
			logger.info("payment is refunded");
		}

		// just build the response and write it to your http response
		String generatedResponse = ipnResponseBuilder.buildResponse(ipnResponse);
		// you can use Apache Commons IO Utils like commented below to write to
		// the response
		// IOUtils.write(generatedResponse, response.getOutputStream());
	}

}

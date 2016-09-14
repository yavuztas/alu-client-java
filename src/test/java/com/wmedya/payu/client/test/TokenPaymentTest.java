package com.wmedya.payu.client.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.wmedya.payu.client.PayuClient;
import com.wmedya.payu.client.PayuResponse;
import com.wmedya.payu.client.TokenPaymentResponse;
import com.wmedya.payu.client.model.Address;
import com.wmedya.payu.client.model.Billing;
import com.wmedya.payu.client.model.Card;
import com.wmedya.payu.client.model.Delivery;
import com.wmedya.payu.client.model.MerchantConfig;
import com.wmedya.payu.client.model.MerchantPlatform;
import com.wmedya.payu.client.model.Order;
import com.wmedya.payu.client.model.PricesCurrency;
import com.wmedya.payu.client.model.Product;
import com.wmedya.payu.client.model.TokenOrder;
import com.wmedya.payu.client.model.TokenType;
import com.wmedya.payu.client.model.User;

public class TokenPaymentTest {

	private Logger logger = Logger.getLogger(TokenPaymentTest.class);

	private MerchantConfig config;

	@Before
	public void setUp() {
		config = new MerchantConfig("YOUR_CODE", "YOUR_SECRET", MerchantPlatform.TR);
	}

	/**
	 * This will be the first payment as same as basic payment with only
	 * difference is enabling token
	 */
	@Test
	public void paymentWithTokenRequestTest() {

		Order order = new Order("123");
		order.setBackRef("http://your.path.to/return");
		// enable token payment and receive a token from ipn on first payment
		order.setEnableTokenPayment(true);
		order.setTokenPaymentType(TokenType.PAY_ON_TIME);

		Product product1 = new Product("Product 1", "PRD1", 10.0, PricesCurrency.TRY);

		// adding products to order
		order.addProduct(product1);

		Billing billing = new Billing("TEKİN", "Yusuf Ziya", "TR");
		billing.setEmail("yusufziyatekin@gmail.com");
		billing.setPhone("123123123");

		Address address = new Address("Somewhere in Turkey line 1", "Somewhere in Turkey line 2", "06100", "ANKARA", "ANKARA");
		billing.setAddress(address);

		// set billing to order
		order.setBilling(billing);

		Delivery delivery = new Delivery("TEKİN", "Yusuf Ziya", "TR");
		delivery.setAddress(address);
		delivery.setCompany("Wmedya");

		// set delivery to order
		order.setDelivery(delivery);

		// or directly with real card details
		Card card = new Card("4355084355084358", "11", "2015", "123", "Yusuf Ziya TEKİN");

		// user with shoppers ip and shoppers browser time in UTC format
		User user = new User("127.0.0.1", "2015-05-27 13:31:52");

		// initiate payu client with config
		PayuClient client = new PayuClient(config);

		// try payment with given order, card and user
		PayuResponse response = client.pay(order, card, user);

		// can check response hash if needed
		if (!client.checkResponseHash(response)) {
			logger.info("Response coming from Payu is not valid !, http response might be changed on the fly :)");
		}

		logger.info("Response status: " + response.getStatus());
		logger.info("Response code: " + response.getReturnCode());
		logger.info("Response message: " + response.getReturnMessage());
		logger.info("Is 3D Secure ?: " + response.is3DSecure());

	}

	/**
	 * This is actual token payment after you acquire and store token from
	 * client's first payment
	 */
	@Test
	public void paymentWithTokenTest() {
		String token = "TOKEN_OF_CLIENT";
		TokenOrder order = new TokenOrder(token);
		order.setExternalRef("124");
		order.setAmount(10.0);
		order.setTimestamp(new Date());

		// initiate payu client with config
		PayuClient client = new PayuClient(config);

		TokenPaymentResponse response = client.pay(order);
		if (!response.getCode().equals("0")) {
			logger.info("Token payment failed");
			// you can debug the raw http response if there was an error
			logger.info(response.getRaw());
		} else {
			logger.info("Token payment successfull");
		}
	}
}

package com.wmedya.payu.client.test;

import java.util.Date;

import com.wmedya.payu.client.PayuClient;
import com.wmedya.payu.client.TokenPaymentResponse;
import com.wmedya.payu.client.model.MerchantConfig;
import com.wmedya.payu.client.model.MerchantPlatform;
import com.wmedya.payu.client.model.TokenOrder;

public class TokenPaymentTest {

	public static void main(String[] args) {

		MerchantConfig config = new MerchantConfig("YOUR_CODE", "YOUR_SECRET", MerchantPlatform.TR);

		// initiate payu client with config
		PayuClient client = new PayuClient(config);

		TokenOrder order = new TokenOrder("11111111");
		order.setExternalRef("111");
		order.setAmount(Double.valueOf("5.0"));
		order.setTimestamp(new Date());

		// try payment with given order, card and user
		TokenPaymentResponse response = client.pay(order);
		System.out.println(response.getRaw());

	}

}

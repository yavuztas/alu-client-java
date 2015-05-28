# alu-client-java
Payu Automatic Live Update (ALU) Client for Java

Basic Payment Sample



		MerchantConfig config = new MerchantConfig("YOUR_CODE", "YOUR_SECRET", MerchantPlatform.TR);
		
		Order order = new Order("123");
		order.setBackRef("http://your.path.to/return");

		Product product1 = new Product("Product 1", "PRD1", 10.0, PricesCurrency.TRY);
		Product product2 = new Product("Product 2", "PRD2", 12.0, PricesCurrency.TRY);

		// adding products to order
		order.addProduct(product1);
		order.addProduct(product2);

		Billing billing = new Billing("TEKİN", "Yusuf Ziya", "TR");
		billing.setEmail("yusufziyatekin@gmail.com");
		// billing.setPhone("123123123");

		Address address = new Address("Somewhere in Turkey line 1", "Somewhere in Turkey line 2", "06100", "ANKARA", "ANKARA");
		billing.setAddress(address);

		// set billing to order
		order.setBilling(billing);

		Delivery delivery = new Delivery("TEKİN", "Yusuf Ziya", "TR");
		delivery.setAddress(address);
		delivery.setCompany("Wmedya");

		// set delivery to order
		order.setDelivery(delivery);

		// you can create card with payu one click payment tokens
		Card card = new Card("OneClickPaymentToken");

		// and also with cvv
		card = new Card("OneClickPaymentToken", "123");

		// or directly with real card details
		card = new Card("4355084355084358", "11", "2015", "123", "Yusuf Ziya TEKİN");

		// user with shoppers ip and shoppers browser time in UTC format
		User user = new User("127.0.0.1", "2015-05-27 13:31:52");

		// initiate payu client with config
		PayuClient client = new PayuClient(config);

		// try payment with given order, card and user
		PayuResponse response = client.pay(order, card, user);

		// can check response hash if needed
		if (!client.checkResponseHash(response)) {
			System.out.println("Response coming from Payu is not valid !, http response might be changed on the fly :)");
		}

		System.out.println("Response status: " + response.getStatus());
		System.out.println("Response code: " + response.getReturnCode());
		System.out.println("Response message: " + response.getReturnMessage());
		System.out.println("Is 3D Secure ?: " + response.is3DSecure());

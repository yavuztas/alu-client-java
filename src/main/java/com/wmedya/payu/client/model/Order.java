package com.wmedya.payu.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true, value = { "products", "billing", "delivery" })
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Product> products = new ArrayList<Product>();
	private Billing billing;
	private Delivery delivery;

	@JsonProperty("ORDER_REF")
	private String orderRef;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	@JsonProperty("ORDER_DATE")
	private Date orderDate = new Date();

	@JsonProperty("PAY_METHOD")
	private PaymentMethod payMethod = PaymentMethod.CCVISAMC;

	@JsonProperty("BACK_REF")
	private String backRef;

	/**
	 * Optionals
	 */
	@JsonProperty("SELECTED_INSTALLMENTS_NUMBER")
	private int installmentsNumber = 1;

	@JsonProperty("CARD_PROGRAM_NAME")
	private String cardProgramName;

	@JsonProperty("ORDER_TIMEOUT")
	private String timeout;

	@JsonProperty("USE_LOYALTY_POINTS")
	private Boolean useLoyaltyPoints;

	@JsonProperty("LOYALTY_POINTS_AMOUNT")
	private String loyaltyPointsAmount;

	@JsonProperty("CAMPAIGN_TYPE")
	private CampaignType campaignType;

	@JsonProperty("ORDER_SHIPPING")
	private String shipping;

	/**
	 * Token payment details
	 */
	@JsonProperty("LU_ENABLE_TOKEN")
	private Boolean enableTokenPayment = false;

	@JsonInclude(Include.NON_DEFAULT)
	@JsonProperty("LU_TOKEN_TYPE")
	private TokenType tokenPaymentType = TokenType.PAY_BY_CLICK;

	public Order() {

	}

	public Order(String orderRef) {
		this.orderRef = orderRef;
	}

	public String getOrderRef() {
		return orderRef;
	}

	public void setOrderRef(String orderRef) {
		this.orderRef = orderRef;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public PaymentMethod getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(PaymentMethod payMethod) {
		this.payMethod = payMethod;
	}

	public String getBackRef() {
		return backRef;
	}

	public void setBackRef(String backRef) {
		this.backRef = backRef;
	}

	public String getCardProgramName() {
		return cardProgramName;
	}

	public void setCardProgramName(String cardProgramName) {
		this.cardProgramName = cardProgramName;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getLoyaltyPointsAmount() {
		return loyaltyPointsAmount;
	}

	public void setLoyaltyPointsAmount(String loyaltyPointsAmount) {
		this.loyaltyPointsAmount = loyaltyPointsAmount;
	}

	public CampaignType getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(CampaignType campaignType) {
		this.campaignType = campaignType;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public boolean isEnableTokenPayment() {
		return (enableTokenPayment != null) ? enableTokenPayment : false;
	}

	public String getEnableTokenPayment() {
		return (enableTokenPayment != null && enableTokenPayment) ? "1" : null;
	}

	public void setEnableTokenPayment(Boolean enableTokenPayment) {
		this.enableTokenPayment = enableTokenPayment;
	}

	public TokenType getTokenPaymentType() {
		return tokenPaymentType;
	}

	public void setTokenPaymentType(TokenType tokenPaymentType) {
		this.tokenPaymentType = tokenPaymentType;
	}

	public List<Product> getProducts() {
		return Collections.unmodifiableList(products);
	}

	public void addProduct(Product product) {
		products.add(product);
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public int getInstallmentsNumber() {
		return installmentsNumber;
	}

	public void setInstallmentsNumber(int installmentsNumber) {
		this.installmentsNumber = installmentsNumber;
	}

	public boolean isUseLoyaltyPoints() {
		return (useLoyaltyPoints != null) ? useLoyaltyPoints : false;
	}

	public String getUseLoyaltyPoints() {
		return (useLoyaltyPoints != null && useLoyaltyPoints) ? "YES" : null;
	}

	public void setUseLoyaltyPoints(Boolean useLoyaltyPoints) {
		this.useLoyaltyPoints = useLoyaltyPoints;
	}

}

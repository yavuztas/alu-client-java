package com.wmedya.payu.client.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("ORDER_PNAME[]")
	private String name;

	@JsonProperty("ORDER_PCODE[]")
	private String code;

	@JsonProperty("ORDER_PRICE[]")
	private Double price;

	@JsonProperty("ORDER_QTY[]")
	private int quantity = 1;

	@JsonProperty("PRICES_CURRENCY")
	private PricesCurrency currency;

	@JsonProperty("ORDER_PINFO[]")
	private String info;

	@JsonProperty("ORDER_VER[]")
	private String version;

	public Product() {
	}

	public Product(String name, String code, Double price, PricesCurrency currency) {
		this.name = name;
		this.code = code;
		this.price = price;
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public PricesCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(PricesCurrency currency) {
		this.currency = currency;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}

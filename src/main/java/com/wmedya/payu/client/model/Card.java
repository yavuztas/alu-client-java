package com.wmedya.payu.client.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Card implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("CC_NUMBER")
	private String number;

	@JsonProperty("EXP_MONTH")
	private String expMonth;

	@JsonProperty("EXP_YEAR")
	private String expYear;

	@JsonProperty("CC_CVV")
	private String cvv;

	@JsonProperty("CC_OWNER")
	private String owner;

	@JsonProperty("CC_TOKEN")
	private String token;

	public Card() {

	}

	public Card(String token) {
		this.token = token;
	}

	public Card(String token, String cvv) {
		this.token = token;
		this.cvv = cvv;
	}

	public Card(String number, String expMonth, String expYear, String cvv, String owner) {
		this.number = number;
		this.expMonth = expMonth;
		this.expYear = expYear;
		this.cvv = cvv;
		this.owner = owner;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

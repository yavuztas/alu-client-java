package com.wmedya.payu.client.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("ADDRESS")
	private String address;

	@JsonProperty("ADDRESS2")
	private String address2;

	@JsonProperty("ZIPCODE")
	private String zipCode;

	@JsonProperty("CITY")
	private String city;

	@JsonProperty("STATE")
	private String state;

	public Address() {
	}

	public Address(String address, String address2, String zipCode, String city, String state) {
		this.address = address;
		this.address2 = address2;
		this.zipCode = zipCode;
		this.city = city;
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}

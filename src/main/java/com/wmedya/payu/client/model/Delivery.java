package com.wmedya.payu.client.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Delivery implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("DELIVERY_LNAME")
	private String lastName;

	@JsonProperty("DELIVERY_FNAME")
	private String firstName;

	@JsonProperty("DELIVERY_EMAIL")
	private String email;

	@JsonProperty("DELIVERY_PHONE")
	private String phone;

	@JsonProperty("DELIVERY_COUNTRYCODE")
	private String countryCode;

	@JsonUnwrapped(prefix = "DELIVERY_")
	private Address address = new Address();

	/**
	 * Optionals
	 */
	@JsonProperty("DELIVERY_COMPANY")
	private String company;

	public Delivery() {

	}

	public Delivery(String lastName, String firstName, String countryCode) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.countryCode = countryCode;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}

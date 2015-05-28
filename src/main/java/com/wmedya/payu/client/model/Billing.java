package com.wmedya.payu.client.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Billing implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("BILL_LNAME")
	private String lastName;

	@JsonProperty("BILL_FNAME")
	private String firstName;

	@JsonProperty("BILL_EMAIL")
	private String email;

	@JsonProperty("BILL_PHONE")
	private String phone;

	@JsonProperty("BILL_COUNTRYCODE")
	private String countryCode;

	@JsonUnwrapped(prefix = "BILL_")
	private Address address = new Address();

	/**
	 * Optionals
	 */
	@JsonUnwrapped(prefix = "BILL_FAX")
	private String fax;

	public Billing() {

	}

	public Billing(String lastName, String firstName, String countryCode) {
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}

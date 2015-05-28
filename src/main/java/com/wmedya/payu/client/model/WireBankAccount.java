package com.wmedya.payu.client.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WireBankAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("BANK_IDENTIFIER")
	private String bankIdentifier;

	@JsonProperty("BANK_ACCOUNT")
	private String bankAccount;

	@JsonProperty("ROUTING_NUMBER")
	private String routingNumber;

	@JsonProperty("IBAN_ACCOUNT")
	private String ibanAccount;

	@JsonProperty("BANK_SWIFT")
	private String bankSwift;

	@JsonProperty("COUNTRY")
	private String country;

	@JsonProperty("WIRE_RECIPIENT_NAME")
	private String wireRecipientName;

	@JsonProperty("WIRE_RECIPIENT_VAT_ID")
	private String wireRecipientVatId;

	public WireBankAccount() {
	}

	public String getBankIdentifier() {
		return bankIdentifier;
	}

	public void setBankIdentifier(String bankIdentifier) {
		this.bankIdentifier = bankIdentifier;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}

	public String getIbanAccount() {
		return ibanAccount;
	}

	public void setIbanAccount(String ibanAccount) {
		this.ibanAccount = ibanAccount;
	}

	public String getBankSwift() {
		return bankSwift;
	}

	public void setBankSwift(String bankSwift) {
		this.bankSwift = bankSwift;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWireRecipientName() {
		return wireRecipientName;
	}

	public void setWireRecipientName(String wireRecipientName) {
		this.wireRecipientName = wireRecipientName;
	}

	public String getWireRecipientVatId() {
		return wireRecipientVatId;
	}

	public void setWireRecipientVatId(String wireRecipientVatId) {
		this.wireRecipientVatId = wireRecipientVatId;
	}

}

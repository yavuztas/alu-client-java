package com.wmedya.payu.client.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("REF_NO")
	private String token;

	@JsonProperty("METHOD")
	private TokenMethod method = TokenMethod.TOKEN_NEWSALE;

	@JsonFormat(pattern = "yyyyMMddHHmmss", timezone = "UTC")
	@JsonProperty("TIMESTAMP")
	private Date timestamp;

	@JsonProperty("EXTERNAL_REF")
	private String externalRef;

	@JsonProperty("AMOUNT")
	private Double amount;

	@JsonProperty("CURRENCY")
	private PricesCurrency currency = PricesCurrency.TRY;

	/**
	 * Optionals
	 */
	@JsonProperty("SELECTED_INSTALLMENTS_NUMBER")
	private Integer installmentsNumber;

	@JsonProperty("CANCEL_REASON")
	private String cancelReason;

	public TokenOrder() {

	}

	public TokenOrder(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenMethod getMethod() {
		return method;
	}

	public void setMethod(TokenMethod method) {
		this.method = method;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getExternalRef() {
		return externalRef;
	}

	public void setExternalRef(String externalRef) {
		this.externalRef = externalRef;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PricesCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(PricesCurrency currency) {
		this.currency = currency;
	}

	public Integer getInstallmentsNumber() {
		return installmentsNumber;
	}

	public void setInstallmentsNumber(Integer installmentsNumber) {
		this.installmentsNumber = installmentsNumber;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

}

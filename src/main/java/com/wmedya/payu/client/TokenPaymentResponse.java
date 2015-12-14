package com.wmedya.payu.client;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class TokenPaymentResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private Double amount;
	private String currency;
	private String message;

	@JsonProperty("tran_ref_no")
	private String transactionRefno;

	@JsonIgnore
	private String raw;

	public TokenPaymentResponse() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTransactionRefno() {
		return transactionRefno;
	}

	public void setTransactionRefno(String transactionRefno) {
		this.transactionRefno = transactionRefno;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

}

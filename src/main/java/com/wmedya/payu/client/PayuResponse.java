package com.wmedya.payu.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wmedya.payu.client.model.WireBankAccount;

@JsonInclude(Include.NON_NULL)
public class PayuResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("REFNO")
	private String refno;

	@JsonProperty("ALIAS")
	private String alias;

	@JsonProperty("STATUS")
	private PayuResponseStatus status;

	@JsonProperty("RETURN_CODE")
	private String returnCode;

	@JsonProperty("RETURN_MESSAGE")
	private String returnMessage;

	@JsonProperty("DATE")
	private String date;

	@JsonProperty("URL_3DS")
	private String url3ds;

	@JsonProperty("AMOUNT")
	private Double amount;

	@JsonProperty("CURRENCY")
	private String currency;

	@JsonInclude(Include.NON_DEFAULT)
	@JsonFormat(shape = Shape.STRING)
	@JsonProperty("INSTALLMENTS_NO")
	private int installmentsNo;

	@JsonProperty("CARD_PROGRAM_NAME")
	private String cardProgramName;

	@JsonProperty("ORDER_REF")
	private String orderRef;

	@JsonProperty("AUTH_CODE")
	private String authCode;

	@JsonProperty("RRN")
	private String rrn;

	@JsonProperty("HASH")
	private String hash;

	@JsonProperty("TOKEN_HASH")
	private String tokenHash;

	@JsonInclude(Include.NON_DEFAULT)
	@JsonProperty("WIRE_ACCOUNTS")
	private List<WireBankAccount> wireAccounts = new ArrayList<WireBankAccount>();

	@JsonIgnore
	private String raw;

	public PayuResponse() {
	}

	public PayuResponse(PayuResponseStatus status, String returnCode, String returnMessage) {
		this.status = status;
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
	}

	public String getRefno() {
		return refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public PayuResponseStatus getStatus() {
		return status;
	}

	public void setStatus(PayuResponseStatus status) {
		this.status = status;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUrl3ds() {
		return url3ds;
	}

	public void setUrl3ds(String url3ds) {
		this.url3ds = url3ds;
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

	public int getInstallmentsNo() {
		return installmentsNo;
	}

	public void setInstallmentsNo(int installmentsNo) {
		this.installmentsNo = installmentsNo;
	}

	public String getCardProgramName() {
		return cardProgramName;
	}

	public void setCardProgramName(String cardProgramName) {
		this.cardProgramName = cardProgramName;
	}

	public String getOrderRef() {
		return orderRef;
	}

	public void setOrderRef(String orderRef) {
		this.orderRef = orderRef;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getTokenHash() {
		return tokenHash;
	}

	public void setTokenHash(String tokenHash) {
		this.tokenHash = tokenHash;
	}

	public List<WireBankAccount> getWireAccounts() {
		return Collections.unmodifiableList(wireAccounts);
	}

	@Override
	public String toString() {
		return "PayuResponse [refno=" + refno + ", status=" + status + ", returnCode=" + returnCode + ", returnMessage=" + returnMessage + ", date="
				+ date + "]";
	}

	@JsonIgnore
	public boolean is3DSecure() {
		return url3ds != null;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

}

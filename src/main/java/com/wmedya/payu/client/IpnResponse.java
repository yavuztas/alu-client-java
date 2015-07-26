package com.wmedya.payu.client;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wmedya.payu.client.model.IpnOrderStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IpnResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("SALEDATE")
	private String saleDate;

	@JsonProperty("PAYMENTDATE")
	private String paymentDate;

	@JsonProperty("COMPLETE_DATE")
	private String completeDate;

	@JsonProperty("REFNO")
	private String refNo;

	@JsonProperty("REFNOEXT")
	private String refNoExternal;

	@JsonProperty("ORDERNO")
	private String orderNo;

	@JsonProperty("ORDERSTATUS")
	private IpnOrderStatus orderStatus;

	@JsonProperty("IPN_CC_TOKEN")
	private String token;

	public IpnResponse() {
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getRefNoExternal() {
		return refNoExternal;
	}

	public void setRefNoExternal(String refNoExternal) {
		this.refNoExternal = refNoExternal;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public IpnOrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(IpnOrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public boolean isSuccessfullyPaid() {
		if (orderStatus == IpnOrderStatus.COMPLETE) {
			return true;
		}
		if (orderStatus == IpnOrderStatus.PAYMENT_RECEIVED) {
			return true;
		}
		return false;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

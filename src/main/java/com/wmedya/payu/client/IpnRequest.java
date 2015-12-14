package com.wmedya.payu.client;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wmedya.payu.client.model.IpnOrderStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IpnRequest implements Serializable {

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

	@JsonProperty("IPN_PID[]")
	private String ipnPid;

	@JsonProperty("IPN_PNAME[]")
	private String ipnPName;

	@JsonProperty("IPN_DATE")
	private String ipnDate;

	@JsonProperty("IPN_CC_TOKEN")
	private String ipnCardToken;

	@JsonProperty("IPN_CC_MASK")
	private String ipnCardMask;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
	@JsonProperty("IPN_CC_EXP_DATE")
	private Date ipnCardExpireDate;

	public IpnRequest() {
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

	public String getIpnPid() {
		return ipnPid;
	}

	public void setIpnPid(String ipnPid) {
		this.ipnPid = ipnPid;
	}

	public String getIpnPName() {
		return ipnPName;
	}

	public void setIpnPName(String ipnPName) {
		this.ipnPName = ipnPName;
	}

	public String getIpnDate() {
		return ipnDate;
	}

	public void setIpnDate(String ipnDate) {
		this.ipnDate = ipnDate;
	}

	public String getIpnCardToken() {
		return ipnCardToken;
	}

	public void setIpnCardToken(String ipnCardToken) {
		this.ipnCardToken = ipnCardToken;
	}

	public String getIpnCardMask() {
		return ipnCardMask;
	}

	public void setIpnCardMask(String ipnCardMask) {
		this.ipnCardMask = ipnCardMask;
	}

	public Date getIpnCardExpireDate() {
		return ipnCardExpireDate;
	}

	public void setIpnCardExpireDate(Date ipnCardExpireDate) {
		this.ipnCardExpireDate = ipnCardExpireDate;
	}

}

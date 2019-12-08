package com.grap.pay.domain;

import java.math.BigDecimal;

public class SMSPaymentRequest {
	
	private String phoneNumber;
	private String merchantPhoneNumber;
	private BigDecimal amount;
	private Integer authCode;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMerchantPhoneNumber() {
		return merchantPhoneNumber;
	}
	public void setMerchantPhoneNumber(String merchantPhoneNumber) {
		this.merchantPhoneNumber = merchantPhoneNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getAuthCode() {
		return authCode;
	}
	public void setAuthCode(Integer authCode) {
		this.authCode = authCode;
	}
	
}

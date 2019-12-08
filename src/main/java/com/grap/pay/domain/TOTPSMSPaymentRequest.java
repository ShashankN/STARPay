package com.grap.pay.domain;

import java.math.BigDecimal;

public class TOTPSMSPaymentRequest {
	
	private String phoneNumber;
	private String merchantPhoneNumber;
	private BigDecimal amount;
	private Integer authCode;
	private Integer totp;
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
	public Integer getTotp() {
		return totp;
	}
	public void setTotp(Integer totp) {
		this.totp = totp;
	}

	
}

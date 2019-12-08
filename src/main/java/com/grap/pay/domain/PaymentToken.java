package com.grap.pay.domain;

import java.math.BigDecimal;

public class PaymentToken {
	
	private String userId;
	private String recepientId;
	private BigDecimal amount;
	private Integer totp;
	private Integer authCode;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRecepientId() {
		return recepientId;
	}
	public void setRecepientId(String recepientId) {
		this.recepientId = recepientId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getTotp() {
		return totp;
	}
	public void setTotp(Integer totp) {
		this.totp = totp;
	}
	public Integer getAuthCode() {
		return authCode;
	}
	public void setAuthCode(Integer authCode) {
		this.authCode = authCode;
	}
	
}

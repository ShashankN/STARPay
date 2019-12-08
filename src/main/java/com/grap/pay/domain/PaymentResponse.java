package com.grap.pay.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {
	
	private String fromPhoneNumber;
	
	private String toPhoneNumber;
	
	private BigDecimal fromBalance;
	
	private BigDecimal toBalance;
	
	public PaymentResponse() {
		
	}
	
	
	public PaymentResponse(String fromPhoneNumber, String toPhoneNumber, BigDecimal fromBalance, BigDecimal toBalance,
			String status, String message, BigDecimal amount) {
		super();
		this.fromPhoneNumber = fromPhoneNumber;
		this.toPhoneNumber = toPhoneNumber;
		this.fromBalance = fromBalance;
		this.toBalance = toBalance;
		this.status = status;
		this.message = message;
		this.amount = amount;
	}



	public PaymentResponse(BigDecimal amount) {
		super();
		this.amount = amount;
	}
	private String status;
	private String message;
	private BigDecimal amount;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getFromPhoneNumber() {
		return fromPhoneNumber;
	}


	public void setFromPhoneNumber(String fromPhoneNumber) {
		this.fromPhoneNumber = fromPhoneNumber;
	}


	public String getToPhoneNumber() {
		return toPhoneNumber;
	}


	public void setToPhoneNumber(String toPhoneNumber) {
		this.toPhoneNumber = toPhoneNumber;
	}


	public BigDecimal getFromBalance() {
		return fromBalance;
	}


	public void setFromBalance(BigDecimal fromBalance) {
		this.fromBalance = fromBalance;
	}


	public BigDecimal getToBalance() {
		return toBalance;
	}


	public void setToBalance(BigDecimal toBalance) {
		this.toBalance = toBalance;
	}
	
	
}

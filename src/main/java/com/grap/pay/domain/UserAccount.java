package com.grap.pay.domain;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.grap.pay.exception.InsufficientBalanceException;

@Document
public class UserAccount {
	
	@Id
	private String userId;
	private String username;
	private String password;
	private BigDecimal threshold;
	private BigDecimal balance;
	private String secretKey;
	private Integer authCode;
	private String phoneNumber;
	private String seed;
	
	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public BigDecimal getThreshold() {
		return threshold;
	}



	public void setThreshold(BigDecimal threshold) {
		this.threshold = threshold;
	}



	public BigDecimal getBalance() {
		return balance;
	}



	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}



	public String getSecretKey() {
		return secretKey;
	}



	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}



	public Integer getAuthCode() {
		return authCode;
	}



	public void setAuthCode(Integer authCode) {
		this.authCode = authCode;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getSeed() {
		return seed;
	}



	public void setSeed(String seed) {
		this.seed = seed;
	}



	public void deductBalance(BigDecimal amount) throws InsufficientBalanceException {
		if(balance.compareTo(amount)<0) {
			throw new InsufficientBalanceException("Available balance "+balance.toPlainString()+" , required balance "+amount.toPlainString());
		}
		balance = balance.subtract(amount);
	}
	
	public void addBalance(BigDecimal amount) {
		balance = balance.add(amount);
	}
	
	
}

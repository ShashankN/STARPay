package com.grap.pay.domain;

public class SMSResponse {
	private String message;
	
	

	public SMSResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

package com.grap.pay.domain;

public class SMS {
	private String secret;
	private String from;
	private String message_id;
	private String message;
	private String sent_timestamp;
	private String sent_to;
	private String device_id;
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSent_timestamp() {
		return sent_timestamp;
	}
	public void setSent_timestamp(String sent_timestamp) {
		this.sent_timestamp = sent_timestamp;
	}
	public String getSent_to() {
		return sent_to;
	}
	public void setSent_to(String sent_to) {
		this.sent_to = sent_to;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	@Override
	public String toString() {
		return "SMS [secret=" + secret + ", from=" + from + ", message_id=" + message_id + ", message=" + message
				+ ", sent_timestamp=" + sent_timestamp + ", sent_to=" + sent_to + ", device_id=" + device_id + "]";
	}
	
	
}

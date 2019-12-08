package com.grap.pay.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.grap.pay.security.InMemoryTOTPService;
import com.grap.pay.security.TokenStore;



public class TokenConfig {
	
	@Value("${grab.token.john}")
	private String  johntoken;
	
	@Value("${grab.token.amit}")
	private String  amittoken;
	
	@Value("${starttime}")
	private Long timeStamp;
	
	@Bean
	public InMemoryTOTPService getInMemoryTOTPService() {
		
		Map<String,TokenStore> tokenStore = new HashMap<String, TokenStore>();
		tokenStore.put("john", new com.grap.pay.security.TokenStore(johntoken, timeStamp));
		tokenStore.put("amit", new com.grap.pay.security.TokenStore(johntoken, timeStamp));
		InMemoryTOTPService otpService = new InMemoryTOTPService(tokenStore);
		return otpService;
	}
	
	
	
}

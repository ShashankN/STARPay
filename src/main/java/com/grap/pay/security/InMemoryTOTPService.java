package com.grap.pay.security;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class InMemoryTOTPService implements TOTPTokenService{

	private Map<String,TokenStore> tokenStore;
	
	public InMemoryTOTPService(Map<String,TokenStore> tokenStore) {
		this.tokenStore = tokenStore;
	}
	@Override
	public Integer getTotp(String userid, Long timeStamp) {
		return tokenStore.get(userid).getToken(timeStamp);
	}

	@Override
	public boolean isValidToken(String userid, Integer token) {
		return tokenStore.get(userid).valaidateToken(System.currentTimeMillis(), token);
	}

}

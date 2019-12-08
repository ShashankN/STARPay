package com.grap.pay.security;

public interface TOTPTokenService {
	
	public Integer getTotp(String userid,Long timeStamp);
	
	public boolean isValidToken(String userid,Integer token);
	
	
}

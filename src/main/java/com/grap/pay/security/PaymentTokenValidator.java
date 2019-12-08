package com.grap.pay.security;

import com.grap.pay.domain.PaymentToken;
import com.grap.pay.exception.InvalidTokenException;
import com.grap.pay.exception.TokenExpiredException;

public interface PaymentTokenValidator {
	public PaymentToken validateToken(String token,String userId) throws TokenExpiredException, InvalidTokenException;
}

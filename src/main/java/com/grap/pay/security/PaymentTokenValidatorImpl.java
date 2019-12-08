package com.grap.pay.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.grap.pay.domain.PaymentToken;
import com.grap.pay.domain.UserAccount;
import com.grap.pay.exception.InvalidTokenException;
import com.grap.pay.exception.TokenExpiredException;
import com.grap.pay.repository.UserAccountRepository;

@Service
public class PaymentTokenValidatorImpl implements PaymentTokenValidator{
	
	@Autowired
	private TOTPTokenService totpTokenService;
	
	@Autowired
	private UserAccountRepository userRepo;
	
	@Override
	public PaymentToken validateToken(String token, String userId) throws TokenExpiredException, InvalidTokenException {
		UserAccount userAccount = userRepo.findByUserId(userId);
		 try {
			PaymentToken paymentToken = JWTUtil.isValidToken(token, userAccount.getSecretKey());
			boolean validToken = totpTokenService.isValidToken(userId, paymentToken.getTotp());
			if(validToken && paymentToken.getAuthCode().equals(userAccount.getAuthCode())) {
				return paymentToken;
			}
			throw new TokenExpiredException();
		} catch (JsonProcessingException e) {
			throw new InvalidTokenException();
		}
		
	}

}

package com.grap.pay.service;

import com.grap.pay.domain.SMS;
import com.grap.pay.exception.InsufficientBalanceException;
import com.grap.pay.exception.InvalidAuthCodeException;
import com.grap.pay.exception.InvalidTokenException;
import com.grap.pay.exception.TokenExpiredException;

public interface SMSService {
	public void processSMS(SMS sms) throws InvalidAuthCodeException, TokenExpiredException, InvalidTokenException, InsufficientBalanceException;
}

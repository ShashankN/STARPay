package com.grap.pay.service;

import com.grap.pay.domain.PaymentRequest;
import com.grap.pay.domain.PaymentResponse;
import com.grap.pay.domain.SMSPaymentRequest;
import com.grap.pay.domain.TOTPSMSPaymentRequest;
import com.grap.pay.exception.InsufficientBalanceException;
import com.grap.pay.exception.InvalidAuthCodeException;
import com.grap.pay.exception.InvalidTokenException;
import com.grap.pay.exception.TokenExpiredException;

public interface PaymentService {
	public PaymentResponse processPayment(PaymentRequest paymentRequest) throws InvalidAuthCodeException, TokenExpiredException, InvalidTokenException, InsufficientBalanceException;
	
	public PaymentResponse processSMSPayment(SMSPaymentRequest smsPaymentRequest) throws  InvalidAuthCodeException, InsufficientBalanceException;
	
	public PaymentResponse processTOTPSMSPayment(TOTPSMSPaymentRequest totpSMSPayment) throws InvalidAuthCodeException, TokenExpiredException, InvalidTokenException, InsufficientBalanceException;
}

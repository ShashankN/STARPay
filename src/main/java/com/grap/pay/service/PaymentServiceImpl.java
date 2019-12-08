package com.grap.pay.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grap.pay.domain.PaymentRequest;
import com.grap.pay.domain.PaymentResponse;
import com.grap.pay.domain.PaymentToken;
import com.grap.pay.domain.SMSPaymentRequest;
import com.grap.pay.domain.TOTPSMSPaymentRequest;
import com.grap.pay.domain.UserAccount;
import com.grap.pay.exception.InsufficientBalanceException;
import com.grap.pay.exception.InvalidAuthCodeException;
import com.grap.pay.exception.InvalidTokenException;
import com.grap.pay.exception.TokenExpiredException;
import com.grap.pay.repository.UserAccountRepository;
import com.grap.pay.security.PaymentTokenValidator;
import com.grap.pay.security.TOTPTokenService;

@Service
public class PaymentServiceImpl implements PaymentService{

	
	private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

	private static final ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private PaymentTokenValidator paymentTokenValidator;
	
	@Autowired
	private UserAccountRepository accountRepo;
	
	@Autowired
	private TOTPTokenService tokenService;
	
	@Override
	public PaymentResponse processPayment(PaymentRequest paymentRequest) throws TokenExpiredException, InvalidTokenException, InsufficientBalanceException {
		String u = paymentRequest.getUserId().equals("amit") ? "john" : "amit";
		paymentRequest.setUserId(u);
		try {
			log.info(" msg "+mapper.writeValueAsString(paymentRequest));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PaymentToken paymentToken = paymentTokenValidator.validateToken(paymentRequest.getPaymentToken(), paymentRequest.getUserId());
		return transfer(paymentToken.getUserId(), paymentToken.getRecepientId(), paymentToken.getAmount());
		//return new PaymentResponse(paymentToken.getAmount());
	}
	
	public PaymentResponse transfer(String fromUserId,String toUserId,BigDecimal amount) throws InsufficientBalanceException {
		UserAccount from = accountRepo.findByUserId(fromUserId);
		UserAccount to = accountRepo.findByUserId(toUserId);
		return transfer(from, to, amount);
	}
	
	public PaymentResponse transfer(UserAccount from,UserAccount to,BigDecimal amount) throws InsufficientBalanceException {
		from.deductBalance(amount);
		to.addBalance(amount);
		accountRepo.save(from);
		accountRepo.save(to);
		return createPaymentResponse(amount,from,to);
	}
	
	public PaymentResponse transferFromPhoneNumber(String fromPhoneNumber,String toPhoneNumber,BigDecimal amount) throws InsufficientBalanceException {
		UserAccount from = accountRepo.findByPhoneNumber(fromPhoneNumber);
		UserAccount to = accountRepo.findByPhoneNumber(toPhoneNumber);
		return transfer(from, to, amount);
	}

	@Override
	public PaymentResponse processSMSPayment(SMSPaymentRequest smsPaymentRequest)
			throws InvalidAuthCodeException, InsufficientBalanceException {
		smsPaymentRequest.getAuthCode();
		UserAccount userAccount = accountRepo.findByPhoneNumber(smsPaymentRequest.getPhoneNumber());
		if(userAccount.getAuthCode().equals(smsPaymentRequest.getAuthCode())) {
			return transferFromPhoneNumber(smsPaymentRequest.getPhoneNumber(), smsPaymentRequest.getMerchantPhoneNumber(), smsPaymentRequest.getAmount());
			//return new PaymentResponse(smsPaymentRequest.getAmount());
		}
		throw new InvalidAuthCodeException();
	}

	@Override
	public PaymentResponse processTOTPSMSPayment(TOTPSMSPaymentRequest totpSMSPayment) throws InvalidAuthCodeException,
			TokenExpiredException, InvalidTokenException, InsufficientBalanceException {
		totpSMSPayment.getAuthCode();
		UserAccount userAccount = accountRepo.findByPhoneNumber(totpSMSPayment.getPhoneNumber());
		if(userAccount.getAuthCode().equals(totpSMSPayment.getAuthCode())) {
			if(tokenService.isValidToken(userAccount.getUserId(), totpSMSPayment.getTotp())){
				return transferFromPhoneNumber(totpSMSPayment.getPhoneNumber(), totpSMSPayment.getMerchantPhoneNumber(), totpSMSPayment.getAmount());
				//return new PaymentResponse(totpSMSPayment.getAmount());
			}
			throw new InvalidTokenException();
		}
		throw new InvalidAuthCodeException();
		
	}

	public PaymentResponse createPaymentResponse(BigDecimal amount,UserAccount fromUser,UserAccount toUser) {
		return new PaymentResponse(fromUser.getPhoneNumber(),toUser.getPhoneNumber(),fromUser.getBalance(),toUser.getBalance(),"ok","Payment success",amount);
		
	}
	

}

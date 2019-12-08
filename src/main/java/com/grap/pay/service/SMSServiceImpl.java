package com.grap.pay.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grap.pay.domain.PaymentRequest;
import com.grap.pay.domain.PaymentResponse;
import com.grap.pay.domain.SMS;
import com.grap.pay.domain.SMSPaymentRequest;
import com.grap.pay.domain.TOTPSMSPaymentRequest;
import com.grap.pay.domain.UserAccount;
import com.grap.pay.exception.InsufficientBalanceException;
import com.grap.pay.exception.InvalidAuthCodeException;
import com.grap.pay.exception.InvalidTokenException;
import com.grap.pay.exception.TokenExpiredException;
import com.grap.pay.repository.UserAccountRepository;
import com.grap.pay.util.SMSUtil;
import com.grap.pay.util.Utils;

@Service
public class SMSServiceImpl implements SMSService{

	@Autowired
	private UserAccountRepository userAccountRepo;
	
	@Autowired
	private PaymentService paymentService;
	
	
	@Override
	public void processSMS(SMS sms) throws InvalidAuthCodeException, TokenExpiredException, InvalidTokenException, InsufficientBalanceException {
		String from = sms.getFrom();
		String message = sms.getMessage();
		String[] keywords = message.split(" ");
		if(keywords.length==1) {
			processPaymentToken(from, message);
		}else if(keywords.length==4) {
			processCodeSMS(from, message);
		}else if(keywords.length==5) {
			processTOTPSMS(from, message);
		}
		
	}
	
	private void sendSuccessMessages(PaymentResponse paymentResp) {
		String fromPhoneNumber = paymentResp.getFromPhoneNumber();
		BigDecimal fromBalance = paymentResp.getFromBalance();
		String toPhoneNumber = paymentResp.getToPhoneNumber();
		String fromMsg = String.format("Paid %s to %s, new balance %s. Transaction code %s", paymentResp.getAmount().toPlainString(),toPhoneNumber,paymentResp.getFromBalance().toPlainString(),Utils.getTransactionCode());
		String sendSMS = SMSUtil.sendSMS(fromPhoneNumber, fromMsg);
		System.out.println("send sms "+sendSMS);
		String toMSG = String.format("Received %s from %s, new balance %s. Transaction code %s ", paymentResp.getAmount().toPlainString(),paymentResp.getFromPhoneNumber(),paymentResp.getToBalance().toPlainString(),Utils.getTransactionCode());
		SMSUtil.sendSMS(toPhoneNumber, toMSG);
	}

	private void processPaymentToken(String phoneNumber, String message) throws InvalidAuthCodeException, TokenExpiredException, InvalidTokenException, InsufficientBalanceException {
		PaymentRequest req = new PaymentRequest();
		req.setPaymentToken(message.trim());
		UserAccount fromAccount = userAccountRepo.findByPhoneNumber(phoneNumber);
		req.setUserId(fromAccount.getUserId());
		PaymentResponse paymentResp = paymentService.processPayment(req);
		sendSuccessMessages(paymentResp);
	}
	
	private void processTOTPSMS(String phoneNumber, String message) throws InvalidAuthCodeException, TokenExpiredException, InvalidTokenException, InsufficientBalanceException {
		String[] keywords = message.split(" ");
		TOTPSMSPaymentRequest totpSMSPayment = new TOTPSMSPaymentRequest();
		totpSMSPayment.setAmount(new BigDecimal(keywords[1]));
		totpSMSPayment.setMerchantPhoneNumber(keywords[2]);
		totpSMSPayment.setAuthCode(Integer.parseInt(keywords[3]));
		totpSMSPayment.setTotp(Integer.parseInt(keywords[4]));
		totpSMSPayment.setPhoneNumber(phoneNumber);
		PaymentResponse paymentResp = paymentService.processTOTPSMSPayment(totpSMSPayment);
		sendSuccessMessages(paymentResp);
		
	}
	
	private void processCodeSMS(String phoneNumber,String message) throws InvalidAuthCodeException, InsufficientBalanceException {
		String[] keywords = message.split(" ");
		SMSPaymentRequest smsReq = new SMSPaymentRequest();
		smsReq.setAmount(new BigDecimal(keywords[1]));
		smsReq.setMerchantPhoneNumber(keywords[2]);
		smsReq.setAuthCode(Integer.parseInt(keywords[3]));
		smsReq.setPhoneNumber(phoneNumber);
		PaymentResponse paymentResp = paymentService.processSMSPayment(smsReq);
		sendSuccessMessages(paymentResp);
	}
	

}

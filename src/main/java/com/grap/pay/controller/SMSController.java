package com.grap.pay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grap.pay.domain.SMS;
import com.grap.pay.domain.SMSResponse;
import com.grap.pay.exception.InsufficientBalanceException;
import com.grap.pay.exception.InvalidAuthCodeException;
import com.grap.pay.exception.InvalidTokenException;
import com.grap.pay.exception.TokenExpiredException;
import com.grap.pay.service.SMSService;
import com.grap.pay.util.SMSUtil;
import com.grap.pay.util.Utils;

@RestController
@CrossOrigin(origins = {"*"})
public class SMSController {
	
	@Autowired
	private SMSService smsService;
	
	
	@PostMapping("/receive/sms")
	public SMSResponse receiveSMS(@RequestBody SMS sms) {
		
		System.out.println(sms);
		
		try {
			smsService.processSMS(sms);
			
		} catch (InvalidAuthCodeException | TokenExpiredException | InvalidTokenException
				| InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			SMSUtil.sendSMS(sms.getFrom(), "Payment failed : "+getErrorMessage(e)+". Transaction id "+Utils.getTransactionCode());
			return new SMSResponse("payment failed");
		}
		return new SMSResponse("payment success");
	}
	
	private String getErrorMessage(Exception e) {
		if(e instanceof InvalidAuthCodeException) {
			return "Invalid auth code";
		}else if(e instanceof TokenExpiredException || e instanceof InvalidTokenException) {
			return "Invalid TOTP";
		}
		else if(e instanceof InsufficientBalanceException) {
			return "Insufficient balance";
		}
		else {
			return "error "+e.getClass().toString();
		}
	}
}

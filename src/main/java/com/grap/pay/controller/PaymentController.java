package com.grap.pay.controller;

import java.io.IOException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grap.pay.domain.PaymentRequest;
import com.grap.pay.domain.PaymentResponse;
import com.grap.pay.domain.SMSPaymentRequest;
import com.grap.pay.domain.TOTPSMSPaymentRequest;
import com.grap.pay.exception.InsufficientBalanceException;
import com.grap.pay.exception.InvalidAuthCodeException;
import com.grap.pay.exception.InvalidTokenException;
import com.grap.pay.exception.TokenExpiredException;
import com.grap.pay.service.PaymentService;

@RestController
@CrossOrigin(origins = {"*"})
public class PaymentController {
	
	
	private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@PostMapping("/payment")
	private ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest request) {
		try {
			return ResponseEntity.ok().body(paymentService.processPayment(request));
		} catch (TokenExpiredException | InvalidTokenException | InsufficientBalanceException | InvalidAuthCodeException e) {
			// TODO Auto-generated catch block
			log.error("payment error : "+getErrorMessage(e));
			PaymentResponse resp = new PaymentResponse();
			resp.setStatus("error");
			resp.setMessage(getErrorMessage(e));
			if(e instanceof InsufficientBalanceException)
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(resp);
			else
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
		}
	}
	
	@PostMapping("/payment/sms")
	private PaymentResponse processSMSPayment(@RequestBody SMSPaymentRequest smsPaymentRequest) {
		return null;
	}
	
	@PostMapping("/payment/totpsms")
	private PaymentResponse processTOTPSMSPayment(@RequestBody TOTPSMSPaymentRequest totpSMSPaymentRequest) {
		return null;
	}
	
	@GetMapping("/payment/process")
	private ResponseEntity<PaymentResponse> proccess(@RequestParam("req") String req) throws JsonParseException, JsonMappingException, IOException {
		byte[] decode = Base64.getDecoder().decode(req);
		return processPayment(mapper.readValue(decode, PaymentRequest.class));
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

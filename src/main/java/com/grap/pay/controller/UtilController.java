package com.grap.pay.controller;

import java.util.Base64;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grap.pay.domain.PaymentToken;
import com.grap.pay.security.TOTPTokenService;

@RestController
public class UtilController {
	
	@Autowired
	private TOTPTokenService tokenService;
	
	@GetMapping("/token")
	public Integer getToken(@RequestParam("u") String user) {
		return tokenService.getTotp(user, Calendar.getInstance().getTimeInMillis());
	}
	
	
	@PostMapping("/token/generate")
	public Map<String,String> generate(@RequestBody TokenReq tokenReq){
		return null;
	}
	
	class TokenReq{
		String secret;
		PaymentToken token;
		public String getSecret() {
			return secret;
		}
		public void setSecret(String secret) {
			this.secret = secret;
		}
		public PaymentToken getToken() {
			return token;
		}
		public void setToken(PaymentToken token) {
			this.token = token;
		}
		
	}
}

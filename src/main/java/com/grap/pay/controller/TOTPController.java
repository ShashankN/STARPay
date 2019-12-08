package com.grap.pay.controller;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalUnit;

import javax.crypto.KeyGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import com.grap.pay.domain.UserAccount;
import com.grap.pay.repository.UserAccountRepository;

@Controller
public class TOTPController {
	
	

	@Autowired
	private UserAccountRepository userRepo;
	
	@RequestMapping(value = "/add2fa", method = RequestMethod.GET)
	public String confirmRegistration(@RequestParam("token") String token) {
		UserAccount user = userRepo.findBySecretKey(token);
		return null;
	}
	
	
	
	
	
}

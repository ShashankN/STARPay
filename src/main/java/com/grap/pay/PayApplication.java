package com.grap.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.grap.pay.security.TOTPTokenService;
import com.grap.pay.service.PaymentServiceImpl;

@SpringBootApplication
@EnableAutoConfiguration
public class PayApplication implements CommandLineRunner{

	public static final String ACCOUNT_SID =
			"AC72230b7233c7775265d2830e8c4cf434";
	public static final String AUTH_TOKEN =
			"7965b183c32556a0bc3b1b1a0076170c";
	
	
	@Autowired
	private TOTPTokenService tokenService;
	
	@Autowired
	private PaymentServiceImpl serviceImpl;
	
	public static void main(String[] args) {
		
		SpringApplication.run(PayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

	
}

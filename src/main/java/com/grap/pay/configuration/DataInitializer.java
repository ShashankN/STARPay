package com.grap.pay.configuration;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.grap.pay.domain.UserAccount;
import com.grap.pay.repository.UserAccountRepository;

@Configuration
public class DataInitializer {
	
	@Autowired
	private UserAccountRepository userRepo;
	
	@Value("${grab.secret.amit}")
	private String amitKey;
	
	@Value("${grab.secret.john}")
	private String johnKey;
	
	
	@PostConstruct
	public void init() {
		
		userRepo.deleteAll();
		
		//merchant
		UserAccount amit  = new UserAccount();
		amit.setBalance(new BigDecimal(100));
		amit.setSecretKey(amitKey);
		amit.setAuthCode(987654);
		amit.setUserId("amit");
		amit.setUsername("Amit");
		amit.setPhoneNumber("+919440667307");
		amit.setSeed("23TplPdS46Juzcyx");
		//amit.setPhoneNumber("+918073560430");
		
		UserAccount john = new UserAccount();
		john.setBalance(new BigDecimal(50));
		john.setSecretKey(johnKey);
		john.setUserId("john");
		john.setUsername("John");
		john.setAuthCode(123456);
		john.setPhoneNumber("+918637618844");
		john.setSeed("23TplPdS46Juzcyx");
		userRepo.save(amit);
		userRepo.save(john);
		
		
		
	}
}

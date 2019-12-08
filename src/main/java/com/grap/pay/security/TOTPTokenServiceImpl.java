package com.grap.pay.security;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.TokenIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import com.grap.pay.domain.UserAccount;
import com.grap.pay.repository.UserAccountRepository;
import com.grap.pay.util.Utils;
import com.j256.twofactorauth.TimeBasedOneTimePasswordUtil;


@Service
@Primary
public class TOTPTokenServiceImpl implements TOTPTokenService{

	@Autowired
	private UserAccountRepository userAcctRepo;
	
	
	private Set<Integer> usedTokens = new HashSet<Integer>();
	@Override
	public Integer getTotp(String userid, Long timeStamp) {
		
			
			UserAccount userAccount = userAcctRepo.findByUserId(userid);
			return getToken(userAccount.getSeed(), Calendar.getInstance().getTimeInMillis());
		
	}
	
	private Integer getToken(String seed,Long timeStamp) {
		try {
			return Integer.parseInt(TimeBasedOneTimePasswordUtil.generateNumberString(seed,timeStamp,30));
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public boolean isValidToken(String userid, Integer token) {
			System.out.println(token);
//			if(!usedTokens.add(token)) {
//				return false;
//			}
			
			UserAccount user = userAcctRepo.findByUserId(userid);
			
			int curToken = getToken(user.getSeed(),Instant.now().toEpochMilli());
			int prevToken = getToken(user.getSeed(),Instant.now().minusSeconds(30).toEpochMilli());
			int nextToken = getToken(user.getSeed(),Instant.now().plusSeconds(30).toEpochMilli());
			System.out.println(""+prevToken+"  "+curToken+" "+nextToken);
			return token == curToken || token == prevToken || token==nextToken;
		
	}

}

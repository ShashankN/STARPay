package com.grap.pay.util;

import java.security.Key;
import java.util.Calendar;

import org.apache.commons.lang3.RandomStringUtils;

import com.grap.pay.domain.UserAccount;

public class Utils {
	public static Long erasePrecision(Long timestamp) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND,0);
		
		return cal.getTimeInMillis();
	}
	
	public static String getTransactionCode() {
		return RandomStringUtils.random(6, true, true);
	}
	
	
public static Key getKey(UserAccount user) {
		
		return new Key() {
			
			@Override
			public String getFormat() {
				return "HEX";
			}
			
			@Override
			public byte[] getEncoded() {
				return user.getSeed().getBytes();
			}
			
			@Override
			public String getAlgorithm() {
				return "HmacSHA1";
			}
		};
	}
}

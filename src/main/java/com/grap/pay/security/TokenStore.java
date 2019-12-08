package com.grap.pay.security;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.grap.pay.util.Utils;

public class TokenStore {
	
	public Map<Long,Integer> tokenList = new TreeMap<Long, Integer>();
	
	public TokenStore(String tokenListMap,long startTimeStamp) {
		
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(startTimeStamp);
		instance.set(Calendar.SECOND, 0);
		instance.set(Calendar.MILLISECOND,0);
		
		List<String> tokens = Arrays.asList(tokenListMap.split(","));
		for (String token : tokens) {
			tokenList.put(instance.getTimeInMillis(),Integer.parseInt(token));
			instance.add(Calendar.MINUTE, 1);
		}
		for (String token : tokens) {
			tokenList.put(instance.getTimeInMillis(),Integer.parseInt(token));
			instance.add(Calendar.MINUTE, 1);
		}
		for (String token : tokens) {
			tokenList.put(instance.getTimeInMillis(),Integer.parseInt(token));
			instance.add(Calendar.MINUTE, 1);
		}
		for (String token : tokens) {
			tokenList.put(instance.getTimeInMillis(),Integer.parseInt(token));
			instance.add(Calendar.MINUTE, 1);
		}
	}
	
	public Integer getToken(Long timeStamp) {
		timeStamp = Utils.erasePrecision(timeStamp);
		return tokenList.get(timeStamp);
	}
	
	public boolean valaidateToken(Long timeStamp,Integer token) {
		timeStamp = Utils.erasePrecision(timeStamp);
		if(tokenList.containsKey(timeStamp)&& tokenList.get(timeStamp).equals(token)) {
			tokenList.remove(timeStamp);
			return true;
		}
		return false;
	}
	
	
}

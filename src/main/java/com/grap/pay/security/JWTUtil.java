package com.grap.pay.security;

import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grap.pay.domain.PaymentToken;

public class JWTUtil {
	public static PaymentToken isValidToken(String token,String secret) throws JsonMappingException, JsonProcessingException {
			
			Algorithm algorithm = Algorithm.HMAC256(secret);
	       
	        JWTVerifier verifier = JWT.require(algorithm)
	            .build(); 
	        DecodedJWT jwt = verifier.verify(token);
	        byte[] decode = Base64.getDecoder().decode(jwt.getPayload());
	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.readValue(new String(decode), PaymentToken.class);
	        
	  
	}
}

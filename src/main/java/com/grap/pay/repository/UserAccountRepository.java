package com.grap.pay.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grap.pay.domain.UserAccount;

public interface UserAccountRepository extends MongoRepository<UserAccount,String>{
	public UserAccount findByUserId(String userId);
	public UserAccount findByPhoneNumber(String phoneNumber);
	public UserAccount findBySecretKey(String secretKey);
}

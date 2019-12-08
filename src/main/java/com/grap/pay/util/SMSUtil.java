package com.grap.pay.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Install the Java helper library from twilio.com/docs/libraries/java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSUtil {
	
	private static final Logger log = LoggerFactory.getLogger(SMSUtil.class);

	// Find your Account Sid and Auth Token at twilio.com/console
	public static final String ACCOUNT_SID =
			"ACdfbaad3eda07f0963be6a84eb42721de";
	public static final String AUTH_TOKEN =
			"9f55bbf51bce78724ec20494b1ccd89b";

	static {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}
	public static String sendSMS(String phoneNumber,String sms) {
		try {
			log.info("sending sms to "+phoneNumber+" message "+sms);
//			Message message = Message
//					.creator(new PhoneNumber(phoneNumber), // to
//							new PhoneNumber("+17245665200"), // from
//							sms)
//					.create();
//
//			String sid = message.getSid();
//			log.info("sent sms "+sid);
//			return sid;
			return "";
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
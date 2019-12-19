package com.trs.cc.sponsor.model;

import com.trs.cc.notification.utils.PushNotificationServer;
import com.trs.cc.notification.utils.SmsServer;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("notification_config")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SponsorAdminConfiguration {

	@Id
	String id;


	// Email Config
	String mailHost;
	String mailPort;
	String fromEmail;
	String mailUserName;
	String mailPassword;
	Boolean mailSmtpAuth;
	Boolean mailStarttls;



	// SMS Server Configuration
	SmsServer smsServer;
	// AWS Server Configuration
	private String smsAwsAccessKey;
	private String smsAwsSecretKey;
	private String smsAwsRegion;


	// Push Notification Configuration
	PushNotificationServer pushNotificationServer;


	private String firebaseServerKey;




	String fileUploadAccessKey;
	String fileUploadSecretKey;
	String bucketName;

	int defaultPageSize;

	String updatedBy;
	Date lastUpdated;
	
}


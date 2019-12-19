package com.trs.cc.sponsor.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("notification_config")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SponsorAdminConfiguration extends PathTrail {

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

	int defaultPageSize;


	
}


package com.trs.cc.sponsor.services;

import com.trs.cc.sponsor.model.SponsorAdminConfiguration;

/**
 * Event Configuration Service to set and update event related configuration from admin persona
 * @author TechRover Solutions.
 *
 */
public interface SponsorAdminConfigService {


	/**
	 * Retrieve Event Configuration.
	 * Single record in mongo collection.
	 * @return
	 */
	SponsorAdminConfiguration getNotificationConfig();

	/**
	 * Event Auth config needs to be created on the application start-up for the very first time.
	 * All other time it will be updated.
	 * @param authConfig
	 * @return : Event Configuration Object.
	 * 
	 */
	SponsorAdminConfiguration updateNotificationAdminConfig(SponsorAdminConfiguration authConfig);
	
	
}

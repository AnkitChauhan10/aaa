package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.model.DiscountCodeAdminConfiguration;

/**
 * Event Configuration Service to set and update event related configuration from admin persona
 * @author TechRover Solutions.
 *
 */
public interface DiscountCodeAdminConfigService {


	/**
	 * Retrieve Event Configuration.
	 * Single record in mongo collection.
	 * @return
	 */
	DiscountCodeAdminConfiguration getNotificationConfig();

	/**
	 * Event Auth config needs to be created on the application start-up for the very first time.
	 * All other time it will be updated.
	 * @param authConfig
	 * @return : Event Configuration Object.
	 * 
	 */
	DiscountCodeAdminConfiguration updateNotificationAdminConfig(DiscountCodeAdminConfiguration authConfig);
	
	
}

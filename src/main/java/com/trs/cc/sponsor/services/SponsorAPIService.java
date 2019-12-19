package com.trs.cc.sponsor.services;

import com.trs.cc.sponsor.model.SponsorAPI;

import java.util.List;

public interface SponsorAPIService {

	/**
	 * ADMIN, DEVELOPERS
	 * Update Auth API related Role, Add new API 
	 * @param authAPI
	 * @return
	 */
	SponsorAPI updateNotificationAPI(SponsorAPI authAPI);
	
	/**
	 * SYSTEM
	 * Check if this Role has access to the API
	 * @param roles
	 * @param api
	 * @return
	 */
	boolean hasAccess(List<String> roles, String api);
	
	
}

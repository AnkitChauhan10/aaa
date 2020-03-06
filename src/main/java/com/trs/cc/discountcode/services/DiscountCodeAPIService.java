package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.model.DiscountCodeAPI;

import java.util.List;

public interface DiscountCodeAPIService {

	/**
	 * ADMIN, DEVELOPERS
	 * Update DiscountCode API related Role, Add new API
	 * @param discountCodeAPI
	 * @return
	 */

	DiscountCodeAPI updateDiscountCodeAPI(DiscountCodeAPI discountCodeAPI);

	/**
	 * SYSTEM
	 * Check if this Role has access to the API
	 * @param roles
	 * @param api
	 * @return
	 */
//	boolean hasAccess(List<String> roles, String api);
	
	
}

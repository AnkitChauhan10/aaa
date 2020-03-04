package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.model.DiscountCodeAPI;
import com.trs.cc.discountcode.repository.DiscountCodeAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiscountCodeAPIServiceImpl implements DiscountCodeAPIService {

	@Autowired
	DiscountCodeAPIRepository discountCodeAPIRepository;


	@Override
	public DiscountCodeAPI updateDiscountCodeAPI(DiscountCodeAPI discountCodeAPI) {
		return discountCodeAPIRepository.save(discountCodeAPI);
	}




	@Override
	public boolean hasAccess(List<String> roles, String api) {
		List<DiscountCodeAPI> access = discountCodeAPIRepository.findByNameAndRolesIn(api, roles);
		return !access.isEmpty();
	}

	
	
}

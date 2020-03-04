package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.model.DiscountCodeAdminConfiguration;
import com.trs.cc.discountcode.repository.DiscountCodeAdminConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountCodeAdminConfigServiceImpl implements DiscountCodeAdminConfigService {

	@Autowired
	DiscountCodeAdminConfigRepository discountCodeAdminConfigRepository;
	
	@Override
	public DiscountCodeAdminConfiguration getNotificationConfig() {
		return discountCodeAdminConfigRepository.findAll().get(0);
	}

	@Override
	public DiscountCodeAdminConfiguration updateNotificationAdminConfig(DiscountCodeAdminConfiguration authConfig) {
		authConfig = discountCodeAdminConfigRepository.save(authConfig);
		return authConfig;
		
	}
	

	
}

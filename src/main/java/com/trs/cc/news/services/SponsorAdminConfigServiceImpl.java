package com.trs.cc.news.services;

import com.trs.cc.news.model.SponsorAdminConfiguration;
import com.trs.cc.news.repository.SponsorAdminConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorAdminConfigServiceImpl implements SponsorAdminConfigService {

	@Autowired
	SponsorAdminConfigRepository sponsorAdminConfigRepository;
	
	@Override
	public SponsorAdminConfiguration getNotificationConfig() {
		return sponsorAdminConfigRepository.findAll().get(0);
	}

	@Override
	public SponsorAdminConfiguration updateNotificationAdminConfig(SponsorAdminConfiguration authConfig) {
		authConfig = sponsorAdminConfigRepository.save(authConfig);
		return authConfig;
		
	}
	

	
}

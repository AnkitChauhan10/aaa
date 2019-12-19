package com.trs.cc.sponsor.services;

import com.trs.cc.sponsor.model.SponsorAdminConfiguration;
import com.trs.cc.sponsor.repository.SponsorAdminConfigRepository;
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

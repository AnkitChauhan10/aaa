package com.trs.cc.news.services;

import com.trs.cc.news.model.SponsorAPI;
import com.trs.cc.news.repository.SponsorAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SponsorAPIServiceImpl implements SponsorAPIService {

	@Autowired
	SponsorAPIRepository sponsorAPIRepository;


	@Override
	public SponsorAPI updateNotificationAPI(SponsorAPI authAPI) {
		return sponsorAPIRepository.save(authAPI);
	}




	@Override
	public boolean hasAccess(List<String> roles, String api) {
		List<SponsorAPI> access = sponsorAPIRepository.findByNameAndRolesIn(api, roles);
		return !access.isEmpty();
	}

	
	
}

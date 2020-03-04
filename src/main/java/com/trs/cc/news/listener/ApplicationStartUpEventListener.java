package com.trs.cc.news.listener;

import com.trs.cc.news.model.SponsorAPI;
import com.trs.cc.news.model.SponsorAdminConfiguration;
import com.trs.cc.news.repository.SponsorAPIRepository;
import com.trs.cc.news.repository.SponsorAdminConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationStartUpEventListener {

	@Value("${spring.data.mongodb.database}")
	String database;

	@Value("${trs.defaults.pagingLimit}")
	String pageLimit;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SponsorAdminConfigRepository sponsorAdminConfigRepository;


	@Autowired
	SponsorAPIRepository sponsorAPIRepository;

	@EventListener()
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.debug("Landed in here"+database);
		System.out.println(database);
		// When application is run for the first time the minimal Auth Config needs to
		// be created from here
		List<SponsorAdminConfiguration> configs = sponsorAdminConfigRepository.findAll();
		if (configs.size() > 0) {
			logger.debug("Notification Module Technical configurations exists");
		} else {

			SponsorAdminConfiguration configuration = new SponsorAdminConfiguration();
			configuration.setDefaultPageSize(Integer.valueOf(pageLimit));


			sponsorAdminConfigRepository.insert(configuration);

			logger.debug("Automatically Creted the Notification Module Technical configurations");
		}

		/* On Application Start up , create the list of authorized services for
		 authorized data*/
//		List<SponsorAPI> notificationAPIS = Utils.getAllMethodNames(NotificationController.class);
//		List<SponsorAPI> configAPIs = Utils.getAllMethodNames(NotificationAdminConfigController.class);
//
//		saveIfNotExits(configAPIs);
//		saveIfNotExits(notificationAPIS);
	}


	private void saveIfNotExits(List<SponsorAPI> apis){
		apis.forEach(api->{
			if(!sponsorAPIRepository.existsByName(api.getName())){
				logger.info("Inserting API : {}",api.getName());
				sponsorAPIRepository.insert(api);
			}
		});

	}

}

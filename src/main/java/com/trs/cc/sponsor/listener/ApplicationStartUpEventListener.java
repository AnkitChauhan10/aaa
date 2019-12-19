package com.trs.cc.sponsor.listener;

import com.trs.cc.notification.controller.NotificationAdminConfigController;
import com.trs.cc.notification.controller.NotificationController;
import com.trs.cc.notification.model.NotificationAPI;
import com.trs.cc.notification.model.NotificationAdminConfiguration;
import com.trs.cc.notification.repository.NotificationAPIRepository;
import com.trs.cc.notification.repository.NotificationAdminConfigRepository;
import com.trs.cc.notification.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class ApplicationStartUpEventListener {

	@Value("${spring.data.mongodb.database}")
	String database;

	@Value("${trs.defaults.pagingLimit}")
	String pageLimit;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	NotificationAdminConfigRepository notificationAdminConfigRepository;


	@Autowired
	NotificationAPIRepository notificationAPIRepository;

	@EventListener()
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.debug("Landed in here"+database);
		System.out.println(database);
		// When application is run for the first time the minimal Auth Config needs to
		// be created from here
		List<NotificationAdminConfiguration> configs = notificationAdminConfigRepository.findAll();
		if (configs.size() > 0) {
			logger.debug("Notification Module Technical configurations exists");
		} else {

			NotificationAdminConfiguration configuration = new NotificationAdminConfiguration();
			configuration.setDefaultPageSize(Integer.valueOf(pageLimit));
			configuration.setUpdatedBy("SYSTEM");
			configuration.setLastUpdated(Calendar.getInstance().getTime());


			notificationAdminConfigRepository.insert(configuration);

			logger.debug("Automatically Creted the Notification Module Technical configurations");
		}

		/* On Application Start up , create the list of authorized services for
		 authorized data*/
		List<NotificationAPI> notificationAPIS = Utils.getAllMethodNames(NotificationController.class);
		List<NotificationAPI> configAPIs = Utils.getAllMethodNames(NotificationAdminConfigController.class);

		saveIfNotExits(configAPIs);
		saveIfNotExits(notificationAPIS);
	}


	private void saveIfNotExits(List<NotificationAPI> apis){
		apis.forEach(api->{
			if(!notificationAPIRepository.existsByName(api.getName())){
				logger.info("Inserting API : {}",api.getName());
				notificationAPIRepository.insert(api);
			}
		});

	}

}

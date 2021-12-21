package com.trs.cc.discountcode.listener;

import com.trs.cc.discountcode.controller.DiscountCodeController;
import com.trs.cc.discountcode.model.DiscountCodeAPI;
import com.trs.cc.discountcode.repository.DiscountCodeAPIRepository;
import com.trs.cc.discountcode.repository.DiscountCodeAdminConfigRepository;
import com.trs.cc.discountcode.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ApplicationStartUpEventListener {

    @Value("${spring.data.mongodb.database}")
    String database;

    @Value("${trs.defaults.pagingLimit}")
    String pageLimit;

    @Autowired
    DiscountCodeAdminConfigRepository discountCodeAdminConfigRepository;


    @Autowired
    DiscountCodeAPIRepository discountCodeAPIRepository;

    @EventListener()
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Landed in here " + database);
        log.info(database);
        // When application is run for the first time the minimal Auth Config needs to
        // be created from here
        /*List<DiscountCodeAdminConfiguration> configs = discountCodeAdminConfigRepository.findAll();
        if (configs.size() > 0) {
            logger.debug("Notification Module Technical configurations exists");
        } else {

            DiscountCodeAdminConfiguration configuration = new DiscountCodeAdminConfiguration();
            configuration.setDefaultPageSize(Integer.valueOf(pageLimit));


            discountCodeAdminConfigRepository.insert(configuration);

            logger.debug("Automatically Creted the Notification Module Technical configurations");
        }*/

		/* On Application Start up , create the list of authorized services for
		 authorized data*/
        List<DiscountCodeAPI> discountCodeAPIS = Utils.getAllMethodNames(DiscountCodeController.class);
        saveIfNotExits(discountCodeAPIS);

//		List<DiscountCodeAPI> configAPIs = Utils.getAllMethodNames(DiscountCodeAdminConfiguration.class);
//		saveIfNotExits(configAPIs);
    }


    private void saveIfNotExits(List<DiscountCodeAPI> apis) {
        apis.forEach(api -> {
            if (!discountCodeAPIRepository.existsByName(api.getName())) {
                log.info("Inserting API : {}", api.getName());
                discountCodeAPIRepository.insert(api);
            }
        });

    }

}

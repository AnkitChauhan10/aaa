package com.trs.cc.sponsor.utils;

import com.trs.cc.notification.services.NotificationAdminConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class GeneralHelper {


    @Autowired
    NotificationAdminConfigService adminConfigService;


    public PageRequest getPagination(Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = adminConfigService.getNotificationConfig().getDefaultPageSize();
            ;
        }
        return PageRequest.of(page, size);
    }



}

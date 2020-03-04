package com.trs.cc.news.utils;

import com.trs.cc.news.services.SponsorAdminConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class GeneralHelper {


    @Autowired
    SponsorAdminConfigService sponsorAdminConfigRepository;


    public PageRequest getPagination(Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = sponsorAdminConfigRepository.getNotificationConfig().getDefaultPageSize();

        }
        return PageRequest.of(page, size);
    }



}

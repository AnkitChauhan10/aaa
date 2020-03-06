package com.trs.cc.discountcode.utils;

import com.trs.cc.discountcode.services.DiscountCodeAdminConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class GeneralHelper {


    @Autowired
    DiscountCodeAdminConfigService discountCodeAdminConfigService;


    public PageRequest getPagination(Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = discountCodeAdminConfigService.getNotificationConfig().getDefaultPageSize();

        }
        return PageRequest.of(page, size);
    }



}

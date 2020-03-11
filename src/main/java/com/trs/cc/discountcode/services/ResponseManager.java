package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.decorator.Response;
import com.trs.cc.discountcode.utils.DescriptionParameter;
import org.springframework.http.HttpStatus;

public interface ResponseManager {
     Response getResponse(HttpStatus code, String status, @DescriptionParameter String description);
}

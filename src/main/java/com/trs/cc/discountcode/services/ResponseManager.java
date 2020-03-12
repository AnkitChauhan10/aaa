package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.decorator.Response;
import com.trs.cc.discountcode.utils.DescriptionParameter;
import org.springframework.http.HttpStatus;

public interface ResponseManager {
     Response getResponse(HttpStatus code, String status, @DescriptionParameter String description);

     Response getOkResponse();

     Response getSuccessResponse();

     Response getCreatedResponse();

     Response getUpdatedResponse();

     Response getDeletedResponse();

     Response getInvalidRequestResponse();

     Response getInvalidDataResponse();

     Response getNotFoundResponse();

     Response getNotFoundResponse(String description);

     Response getErrorResponse(String message, HttpStatus statusCode);

     Response getSuccessResponse(String message, HttpStatus statusCode);
}

package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.decorator.Response;
import com.trs.cc.discountcode.utils.DescriptionParameter;
import org.springframework.http.HttpStatus;

public interface ResponseManager {
     Response getResponse(HttpStatus code, String status, @DescriptionParameter String description);

     Response getOkResponse();

     Response getSuccessResponse();
     Response getSuccessResponse(String description);

     Response getCreatedResponse();

     Response getUpdatedResponse();

     Response getDeletedResponse();
     Response getDeletedResponse(String description);

     Response getInvalidRequestResponse();
     Response getInvalidRequestResponse(String description);


     Response getNotFoundResponse();

     Response getNotFoundResponse(String description);

     Response getErrorResponse(String message, HttpStatus statusCode);

     Response getInternalServerErrorResponse();
     Response getInternalServerErrorResponse(String description);

     Response getSuccessResponse(String message, HttpStatus statusCode);
}

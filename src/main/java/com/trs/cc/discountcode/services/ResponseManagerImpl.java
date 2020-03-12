package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.decorator.Response;
import com.trs.cc.discountcode.utils.DescriptionParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.trs.cc.discountcode.constant.ResponseConstant.*;

@Service
public class ResponseManagerImpl implements ResponseManager {

    @Autowired
    ResponseManager responseManager;

    @Override
    public Response getResponse(HttpStatus code, String status, @DescriptionParameter String description) {
        // method for aspect advice
        return new Response(code, status, description);
    }

    @Override
    public Response getOkResponse(){
        return responseManager.getResponse(HttpStatus.OK, OK, OK_DESCRIPTION);
    }

    @Override
    public Response getSuccessResponse() {
        return responseManager.getResponse(HttpStatus.OK, SUCCESS, OK_DESCRIPTION);
    }

    @Override
    public Response getCreatedResponse(){
        return responseManager.getResponse(HttpStatus.CREATED, SUCCESS, CREATED_DESCRIPTION);
    }

    @Override
    public Response getUpdatedResponse(){
        return responseManager.getResponse(HttpStatus.OK, UPDATED, UPDATE_DESCRIPTION);
    }

    @Override
    public Response getDeletedResponse(){
        return responseManager.getResponse(HttpStatus.OK, DELETED, DELETED_DESCRIPTION);
    }

    @Override
    public Response getInvalidRequestResponse(){
        return responseManager.getResponse(HttpStatus.BAD_REQUEST, ERROR, INVALID_REQUEST_DESCRIPTION);
    }

    @Override
    public Response getInvalidDataResponse(){
        return responseManager.getResponse(HttpStatus.BAD_REQUEST, ERROR, INVALID_DATA_DESCRIPTION);
    }

    @Override
    public Response getNotFoundResponse(){
        return responseManager.getResponse(HttpStatus.BAD_REQUEST, ERROR, NOT_FOUND_DESCRIPTION);
    }

    @Override
    public Response getNotFoundResponse(String description){
        return responseManager.getResponse(HttpStatus.BAD_REQUEST, ERROR, description);
    }

    @Override
    public Response getErrorResponse(String message, HttpStatus statusCode) {
        return responseManager.getResponse(statusCode, ERROR, message);
    }

    @Override
    public Response getSuccessResponse(String message, HttpStatus statusCode) {
        return responseManager.getResponse(statusCode, SUCCESS, message);
    }

}

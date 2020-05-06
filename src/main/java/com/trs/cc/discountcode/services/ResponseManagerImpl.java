package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.decorator.RequestSession;
import com.trs.cc.discountcode.decorator.Response;
import com.trs.cc.discountcode.utils.DescriptionParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.trs.cc.discountcode.constant.ResponseConstant.*;

@Service
public class ResponseManagerImpl implements ResponseManager {

    @Autowired
    MessageSource messageSource;

    @Autowired
    RequestSession requestSession;



    @Override
    public Response getResponse(HttpStatus code, String status, @DescriptionParameter String description) {
        // method for aspect advice
        description = messageSource.getMessage(description, null, requestSession.getLocale());
        return new Response(code, status, description);
    }

    @Override
    public Response getOkResponse(){
        return getResponse(HttpStatus.OK, OK, OK_DESCRIPTION);
    }

    @Override
    public Response getSuccessResponse() {
        return getResponse(HttpStatus.OK, SUCCESS, OK_DESCRIPTION);
    }

    @Override
    public Response getSuccessResponse(String description) {
        return getResponse(HttpStatus.OK, SUCCESS, description);
    }

    @Override
    public Response getCreatedResponse(){
        return getResponse(HttpStatus.CREATED, SUCCESS, CREATED_DESCRIPTION);
    }

    @Override
    public Response getUpdatedResponse(){
        return getResponse(HttpStatus.OK, UPDATED, UPDATE_DESCRIPTION);
    }

    @Override
    public Response getDeletedResponse() {
        return getResponse(HttpStatus.OK, DELETED, DELETED_DESCRIPTION);
    }

    @Override
    public Response getDeletedResponse(String description){
        return getResponse(HttpStatus.OK, DELETED, description);
    }

    @Override
    public Response getInvalidRequestResponse(){
        return getResponse(HttpStatus.BAD_REQUEST, ERROR, INVALID_REQUEST_DESCRIPTION);
    }

    @Override
    public Response getInvalidRequestResponse(String description){
        return getResponse(HttpStatus.BAD_REQUEST, ERROR, description);
    }

    @Override
    public Response getNotFoundResponse(){
        return getResponse(HttpStatus.NOT_FOUND, ERROR, NOT_FOUND_DESCRIPTION);
    }

    @Override
    public Response getNotFoundResponse(String description){
        return getResponse(HttpStatus.NOT_FOUND, ERROR, description);
    }

    @Override
    public Response getErrorResponse(String message, HttpStatus statusCode) {
        return getResponse(statusCode, ERROR, message);
    }

    @Override
    public Response getInternalServerErrorResponse() {
        return getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @Override
    public Response getInternalServerErrorResponse(String description) {
        return getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ERROR, description);
    }

    @Override
    public Response getSuccessResponse(String message, HttpStatus statusCode) {
        return getResponse(statusCode, SUCCESS, message);
    }

}

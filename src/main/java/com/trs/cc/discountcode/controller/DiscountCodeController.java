package com.trs.cc.discountcode.controller;

import com.trs.cc.discountcode.constant.MessageConstants;
import com.trs.cc.discountcode.constant.ResponseConstant;
import com.trs.cc.discountcode.decorator.*;
import com.trs.cc.discountcode.exception.*;
import com.trs.cc.discountcode.services.DiscountCodeService;
import com.trs.cc.discountcode.services.ResponseManager;
import com.trs.cc.discountcode.utils.Access;
import com.trs.cc.discountcode.utils.Roles;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.ResourceBundle;

@RestController
@RequestMapping("discount-code-controller")
@Api
public class DiscountCodeController {
    @Autowired
    DiscountCodeService discountCodeService;
    @Autowired
    RequestSession requestSession;
    @Autowired
    Response response;

    @RequestMapping(name = "addDiscountCode", value = "/addDiscountCode", method = RequestMethod.POST)
    @Access(levels = {Roles.SYSTEM})
    public DataResponse<DiscountCodeResponse> addDiscountCode(
            @RequestBody DiscountCodeRequest discountCodeRequest
    ) {
        DataResponse<DiscountCodeResponse> dataResponse = new DataResponse<>();
        try {
            dataResponse.setData(discountCodeService.addDiscountCode(discountCodeRequest));
            dataResponse.setStatus(Response.getSuccessResponse());
        } catch (AlreadyExistException e) {
            dataResponse.setStatus(Response.getErrorResponse(e.getMessage(), HttpStatus.CONFLICT));
        }
        return dataResponse;
    }

    @RequestMapping(name = "updateDiscountCode", value = "/updateDiscountCode/{discountCodeId}", method = RequestMethod.POST)
    @Access(levels = {Roles.SYSTEM})
    public DataResponse<DiscountCodeResponse> updateDiscountCode(
            @PathVariable String discountCodeId,
            @RequestBody DiscountCodeRequest updateDiscountCodeRequest
    ) {
        DataResponse<DiscountCodeResponse> dataResponse = new DataResponse<>();
        try {
            dataResponse.setData(discountCodeService.updateDiscountCode(discountCodeId, updateDiscountCodeRequest));
            dataResponse.setStatus(Response.getUpdatedResponse());
        } catch (NotFoundException e) {
            dataResponse.setStatus(Response.getErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND));
        } catch (InvocationTargetException | IllegalAccessException e) {
            dataResponse.setStatus(Response.getErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return dataResponse;
    }

    @RequestMapping(name = "deleteDiscountCode", value = "/deleteDiscountCode/{discountCodeId}", method = RequestMethod.POST)
    @Access(levels = {Roles.SYSTEM})
    public DataResponse<String> deleteDiscountCode(
            @PathVariable String discountCodeId
    ) {
        DataResponse<String> dataResponse = new DataResponse<>();
        try {
            discountCodeService.deleteDiscountCode(discountCodeId);
            dataResponse.setData(MessageConstants.DISCOUNT_CODE_DELETED_SUCCESSFULLY);
            dataResponse.setStatus(Response.getDeletedResponse());
        } catch (NotFoundException e) {
            dataResponse.setStatus(Response.getErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND));
        }
        return dataResponse;
    }

    @RequestMapping(name = "useDiscountCode", value = "/useDiscountCode", method = RequestMethod.POST)
    @Access(levels = {Roles.SYSTEM})
    public DataResponse<String> useDiscountCode(
            @RequestBody DiscountCodeUseRequest discountCodeUseRequest
    ) {
        DataResponse<String> dataResponse = new DataResponse<>();
        try {
            discountCodeService.useDiscountCode(requestSession.getJwtUser().getId(), discountCodeUseRequest);
            dataResponse.setData(MessageConstants.DISCOUNT_CODE_ACCEPTED_SUCCESSFULLY);
            dataResponse.setStatus(Response.getOkResponse());
        } catch (NotFoundException e) {
            dataResponse.setStatus(Response.getNotFoundResponse(e.getMessage()));
        } catch (AuthException e) {
            dataResponse.setStatus(Response.getErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN));
        } catch (CodeUsageLimitException e) {
            dataResponse.setStatus(Response.getErrorResponse(e.getMessage(), HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS));
        } catch (TimeLimitExceedException e) {
            dataResponse.setStatus(Response.getErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST));
        }
        return dataResponse;
    }

    @RequestMapping(name = "getDiscountCodeList", value = "/getDiscountCodeList", method = RequestMethod.GET)
    @Access(levels = {Roles.SYSTEM})
    public DataResponse getDiscountCodeList() {
        return new DataResponse<>(discountCodeService.getDiscountCodeList(), response.getResponse(HttpStatus.OK, ResponseConstant.OK, ResponseConstant.OK_DESCRIPTION));
    }

}

package com.trs.cc.discountcode.controller;

import com.trs.cc.discountcode.constant.MessageConstants;
import com.trs.cc.discountcode.constant.ResponseConstant;
import com.trs.cc.discountcode.decorator.*;
import com.trs.cc.discountcode.exception.AlreadyExistException;
import com.trs.cc.discountcode.exception.AuthException;
import com.trs.cc.discountcode.exception.CodeUsageLimitException;
import com.trs.cc.discountcode.exception.NotFoundException;
import com.trs.cc.discountcode.services.DiscountCodeService;
import com.trs.cc.discountcode.utils.Access;
import com.trs.cc.discountcode.utils.Roles;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("discount-code-controller")
@Api
public class DiscountCodeController {
    @Autowired
    DiscountCodeService discountCodeService;
    @Autowired
    RequestSession requestSession;

    @RequestMapping(name = "addDiscountCode", value = "/addDiscountCode", method = RequestMethod.POST)
    @Access(levels = {Roles.SYSTEM})
    public DataResponse<DiscountCodeResponse> addDiscountCode(
            @RequestBody DiscountCodeRequest discountCodeRequest
    ) {
        DataResponse<DiscountCodeResponse> dataResponse = new DataResponse<>();
        try {
            dataResponse.setData(discountCodeService.addDiscountCode(discountCodeRequest));
            dataResponse.setStatus(ResponseConstant.OK_RESPONSE);
        } catch (AlreadyExistException e) {
            dataResponse.setStatus(new Response(HttpStatus.CONFLICT, ResponseConstant.FAIL, e.getLocalizedMessage()));
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
            dataResponse.setStatus(ResponseConstant.UPDATE_RESPONSE);
        } catch (NotFoundException e) {
            dataResponse.setStatus(new Response(HttpStatus.NOT_FOUND, ResponseConstant.FAIL, e.getLocalizedMessage()));
        } catch (InvocationTargetException | IllegalAccessException e) {
            dataResponse.setStatus(new Response(HttpStatus.INTERNAL_SERVER_ERROR, ResponseConstant.FAIL, e.getLocalizedMessage()));
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
            dataResponse.setStatus(ResponseConstant.DELETE_RESPONSE);
        } catch (NotFoundException e) {
            dataResponse.setStatus(new Response(HttpStatus.NOT_FOUND, ResponseConstant.FAIL, e.getLocalizedMessage()));
        }
        return dataResponse;
    }

    @RequestMapping(name = "useDiscountCode", value = "/useDiscountCode", method = RequestMethod.POST)
    @Access(levels = {Roles.SYSTEM})
    public DataResponse<UseDiscountCodeResponse> useDiscountCode(
            @RequestBody UseDiscountCodeRequest useDiscountCodeRequest
    ) {
        DataResponse<UseDiscountCodeResponse> dataResponse = new DataResponse<>();
        try {
            dataResponse.setData(discountCodeService.useDiscountCode(requestSession.getJwtUser().getId(), useDiscountCodeRequest));
            dataResponse.setStatus(ResponseConstant.OK_RESPONSE);
        } catch (NotFoundException e) {
            dataResponse.setStatus(new Response(HttpStatus.NOT_FOUND, ResponseConstant.FAIL, e.getLocalizedMessage()));
        } catch (AuthException e) {
            dataResponse.setStatus(new Response(HttpStatus.FORBIDDEN, ResponseConstant.FAIL, e.getLocalizedMessage()));
        } catch (CodeUsageLimitException e) {
            dataResponse.setStatus(new Response(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, ResponseConstant.FAIL, e.getLocalizedMessage()));
        }
        return dataResponse;
    }

}

package com.trs.cc.discountcode.controller;

import com.trs.cc.discountcode.constant.MessageConstants;
import com.trs.cc.discountcode.decorator.*;
import com.trs.cc.discountcode.exception.*;
import com.trs.cc.discountcode.model.DiscountCode;
import com.trs.cc.discountcode.services.DiscountCodeService;
import com.trs.cc.discountcode.services.ResponseManager;
import com.trs.cc.discountcode.utils.Access;
import com.trs.cc.discountcode.utils.Roles;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api
public class DiscountCodeController {

    @Autowired
    DiscountCodeService discountCodeService;

    @Autowired
    RequestSession requestSession;

    @Autowired
    ResponseManager responseManager;

    @RequestMapping(name = "addDiscountCode", value = "/add", method = RequestMethod.POST)
    @Access(levels = {Roles.SYSTEM})
    public DataResponse<DiscountCodeResponse> addDiscountCode(@RequestBody DiscountCodeRequest discountCodeRequest) {
        DataResponse<DiscountCodeResponse> dataResponse = new DataResponse<>();
        try {
            dataResponse.setData(discountCodeService.addDiscountCode(discountCodeRequest));
            dataResponse.setStatus(responseManager.getSuccessResponse());
        } catch (AlreadyExistException e) {
            dataResponse.setStatus(responseManager.getErrorResponse(e.getMessage(), HttpStatus.CONFLICT));
        } catch (Exception e) {
            e.printStackTrace();
            dataResponse.setStatus(responseManager.getInternalServerErrorResponse());
        }
        return dataResponse;
    }

    @RequestMapping(name = "updateDiscountCode", value = "/update/{discountCodeId}", method = RequestMethod.POST)
    @Access(levels = {Roles.SYSTEM})
    public DataResponse<DiscountCodeResponse> updateDiscountCode(@PathVariable String discountCodeId, @RequestBody DiscountCodeRequest updateDiscountCodeRequest) {
        DataResponse<DiscountCodeResponse> dataResponse = new DataResponse<>();
        try {
            dataResponse.setData(discountCodeService.updateDiscountCode(discountCodeId, updateDiscountCodeRequest));
            dataResponse.setStatus(responseManager.getUpdatedResponse());
        } catch (NotFoundException e) {
            dataResponse.setStatus(responseManager.getNotFoundResponse(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            dataResponse.setStatus(responseManager.getInternalServerErrorResponse());
        }
        return dataResponse;
    }

    @RequestMapping(name = "deleteDiscountCode", value = "/delete/{discountCodeId}", method = {RequestMethod.DELETE,RequestMethod.GET})
    @Access(levels = {Roles.SYSTEM})
    public DataResponse deleteDiscountCode(@PathVariable String discountCodeId) {
        DataResponse dataResponse = new DataResponse();
        try {
            discountCodeService.deleteDiscountCode(discountCodeId);
            dataResponse.setStatus(responseManager.getDeletedResponse(MessageConstants.DISCOUNT_CODE_DELETED_SUCCESSFULLY));
        } catch (NotFoundException e) {
            dataResponse.setStatus(responseManager.getNotFoundResponse(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            dataResponse.setStatus(responseManager.getInternalServerErrorResponse());
        }
        return dataResponse;
    }

    @RequestMapping(name = "useDiscountCode", value = "/use", method = RequestMethod.POST)
    @Access(levels = {Roles.SYSTEM})
    public DataResponse<DiscountCode> useDiscountCode(@RequestBody DiscountCodeUseRequest discountCodeUseRequest) {
        DataResponse<DiscountCode> dataResponse = new DataResponse<>();
        try {
            DiscountCode discountCode = discountCodeService.useDiscountCode(requestSession.getJwtUser().getId(), discountCodeUseRequest);
            dataResponse.setData(discountCode);
            dataResponse.setStatus(responseManager.getSuccessResponse(MessageConstants.DISCOUNT_CODE_ACCEPTED_SUCCESSFULLY));
        } catch (NotFoundException e) {
            dataResponse.setStatus(responseManager.getNotFoundResponse(e.getMessage()));
        } catch (AuthException e) {
            dataResponse.setStatus(responseManager.getErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED));
        } catch (CodeUsageLimitException e) {
            dataResponse.setStatus(responseManager.getErrorResponse(e.getMessage(), HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS));
        } catch (TimeLimitExceedException e) {
            dataResponse.setStatus(responseManager.getInvalidRequestResponse(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            dataResponse.setStatus(responseManager.getInternalServerErrorResponse());
        }
        return dataResponse;
    }

    @RequestMapping(name = "saveDiscountCode", value = "/save", method = RequestMethod.POST)
    @Access(levels = {Roles.SYSTEM})
    public DataResponse<DiscountCode> saveDiscountCode(@RequestBody DiscountCode discountCode) {
        DataResponse<DiscountCode> dataResponse = new DataResponse<>();
        try {
            dataResponse.setData(discountCodeService.save(discountCode));
            dataResponse.setStatus(responseManager.getOkResponse());
        } catch (Exception e) {
            e.printStackTrace();
            dataResponse.setStatus(responseManager.getInternalServerErrorResponse());
        }
        return dataResponse;
    }

    @RequestMapping(name = "getDiscountCodeList", value = "/getAll", method = RequestMethod.GET)
    @Access(levels = {Roles.SYSTEM})
    public DataResponse getDiscountCodeList() {
        return new DataResponse<>(discountCodeService.getDiscountCodeList(), responseManager.getSuccessResponse());
    }

}

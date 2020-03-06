package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.decorator.DiscountCodeRequest;
import com.trs.cc.discountcode.decorator.DiscountCodeResponse;
import com.trs.cc.discountcode.decorator.UseDiscountCodeRequest;
import com.trs.cc.discountcode.decorator.UseDiscountCodeResponse;
import com.trs.cc.discountcode.exception.AlreadyExistException;
import com.trs.cc.discountcode.exception.AuthException;
import com.trs.cc.discountcode.exception.CodeUsageLimitException;
import com.trs.cc.discountcode.exception.NotFoundException;
import com.trs.cc.discountcode.model.DiscountCode;

import java.lang.reflect.InvocationTargetException;

public interface DiscountCodeService {
    DiscountCodeResponse addDiscountCode(DiscountCodeRequest discountCodeRequest) throws AlreadyExistException;

    DiscountCodeResponse updateDiscountCode(String discountCodeId, DiscountCodeRequest discountCodeRequest) throws NotFoundException, InvocationTargetException, IllegalAccessException;

    void deleteDiscountCode(String discountCodeId) throws NotFoundException;

    UseDiscountCodeResponse useDiscountCode(String systemToken, UseDiscountCodeRequest useDiscountCodeRequest) throws NotFoundException, AuthException, CodeUsageLimitException;

    DiscountCode findDiscountCodeById(String discountCodeId) throws NotFoundException;
}

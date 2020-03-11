package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.decorator.DiscountCodeRequest;
import com.trs.cc.discountcode.decorator.DiscountCodeResponse;
import com.trs.cc.discountcode.decorator.DiscountCodeUseRequest;
import com.trs.cc.discountcode.decorator.DiscountCodeUseResponse;
import com.trs.cc.discountcode.exception.*;
import com.trs.cc.discountcode.model.DiscountCode;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface DiscountCodeService {
    DiscountCodeResponse addDiscountCode(DiscountCodeRequest discountCodeRequest) throws AlreadyExistException;

    DiscountCodeResponse updateDiscountCode(String discountCodeId, DiscountCodeRequest discountCodeRequest) throws NotFoundException, InvocationTargetException, IllegalAccessException;

    void deleteDiscountCode(String discountCodeId) throws NotFoundException;

    void useDiscountCode(String systemToken, DiscountCodeUseRequest discountCodeUseRequest) throws NotFoundException, AuthException, CodeUsageLimitException, TimeLimitExceedException;

    List<DiscountCode> getDiscountCodeList();

    DiscountCode findDiscountCodeById(String discountCodeId) throws NotFoundException;
}

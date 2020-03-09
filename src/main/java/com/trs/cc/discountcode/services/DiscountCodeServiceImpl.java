package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.constant.ExceptionConstant;
import com.trs.cc.discountcode.decorator.DiscountCodeRequest;
import com.trs.cc.discountcode.decorator.DiscountCodeResponse;
import com.trs.cc.discountcode.decorator.DiscountCodeUseRequest;
import com.trs.cc.discountcode.decorator.DiscountCodeUseResponse;
import com.trs.cc.discountcode.exception.*;
import com.trs.cc.discountcode.model.DiscountCode;
import com.trs.cc.discountcode.model.DiscountCodeLog;
import com.trs.cc.discountcode.repository.DiscountCodeLogRepository;
import com.trs.cc.discountcode.repository.DiscountCodeRepository;
import com.trs.cc.discountcode.utils.JwtTokenUtil;
import com.trs.cc.discountcode.utils.NullAwareBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountCodeServiceImpl implements DiscountCodeService {
    @Autowired
    DiscountCodeRepository discountCodeRepository;
    @Autowired
    DiscountCodeLogRepository discountCodeLogRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private BeanUtilsBean copyNotNullProps = new NullAwareBean();

    @Override
    public DiscountCodeResponse addDiscountCode(DiscountCodeRequest discountCodeRequest) throws AlreadyExistException {
        if (discountCodeRepository.existsByDiscountCodeAndSoftDeleteIsFalse(discountCodeRequest.getDiscountCode())) throw new AlreadyExistException(ExceptionConstant.DISCOUNT_CODE_ALREADY_EXISTS);
        if (discountCodeRepository.existsByNameAndSoftDeleteIsFalse(discountCodeRequest.getName())) throw new AlreadyExistException(ExceptionConstant.DISCOUNT_CODE_NAME_ALREADY_EXISTS);

        DiscountCode discountCode = modelMapper.map(discountCodeRequest, DiscountCode.class);
        discountCode = discountCodeRepository.save(discountCode);

        return modelMapper.map(discountCode, DiscountCodeResponse.class);
    }

    @Override
    public DiscountCodeResponse updateDiscountCode(String discountCodeId, DiscountCodeRequest discountCodeRequest) throws NotFoundException, InvocationTargetException, IllegalAccessException {
        DiscountCode discountCode = findDiscountCodeById(discountCodeId);

        copyNotNullProps.copyProperties(discountCode, discountCodeRequest);
        discountCode = discountCodeRepository.save(discountCode);

        return modelMapper.map(discountCode, DiscountCodeResponse.class);
    }

    @Override
    public void deleteDiscountCode(String discountCodeId) throws NotFoundException {
        DiscountCode discountCode = findDiscountCodeById(discountCodeId);
        discountCode.setSoftDelete(true);
        discountCodeRepository.save(discountCode);
    }

    @Override
    public void useDiscountCode(String module, DiscountCodeUseRequest discountCodeUseRequest) throws NotFoundException, AuthException, CodeUsageLimitException, TimeLimitExceedException {

        String userId = discountCodeUseRequest.getUserId();
        String discountCode = discountCodeUseRequest.getDiscountCode();

        // find discount code data by code
        Optional<DiscountCode> optionalDiscountCode = discountCodeRepository.findByDiscountCodeAndSoftDeleteIsFalse(discountCode);
        if (!optionalDiscountCode.isPresent()) {
            throw new NotFoundException(ExceptionConstant.DISCOUNT_CODE_NOT_EXISTS);
        }
        DiscountCode discountCodeData = optionalDiscountCode.get();

        // check expiration
        Date todayDate = new Date();
        Date expirationDate = discountCodeData.getExpirationDate();
        if (todayDate.before(expirationDate)) {
            throw new TimeLimitExceedException(ExceptionConstant.DISCOUNT_CODE_EXPIRES);
        }

        // check user access if any exists
        List<String> users = discountCodeData.getUsers();
        if (null != users && !users.contains(userId)) { // if users specification exists and user not exists
            throw new AuthException(ExceptionConstant.USER_NOT_ALLOWED);
        }

        // check modules permission
        List<String> modules = discountCodeData.getModules();
        modules = modules == null ? new ArrayList<>() : modules;
        if (!modules.contains(module)) {
            throw new AuthException(ExceptionConstant.MODULE_HAVE_NOT_ACCESS);
        }

        // check usage and update usage
        int maxUse = discountCodeData.getNoOfMaxUsage();
        int used = discountCodeData.getUsageCount();
        if (maxUse == used) {
            throw new CodeUsageLimitException(ExceptionConstant.DISCOUNT_CODE_USAGE_LIMIT_EXCEEDS);
        }
        used += 1;

        discountCodeData.setUsageCount(used);
        discountCodeRepository.save(discountCodeData);

        DiscountCodeLog discountCodeLog = new DiscountCodeLog(null, discountCodeData.getId(), userId, module);
        discountCodeLogRepository.save(discountCodeLog);
    }

    @Override
    public DiscountCode findDiscountCodeById(String discountCodeId) throws NotFoundException {
        Optional<DiscountCode> discountCode = discountCodeRepository.findByIdAndSoftDeleteIsFalse(discountCodeId);
        if (!discountCode.isPresent()) {
            throw new NotFoundException(ExceptionConstant.DISCOUNT_CODE_NOT_EXISTS);
        }
        return discountCode.get();
    }
}

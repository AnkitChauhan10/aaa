package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.constant.ExceptionConstant;
import com.trs.cc.discountcode.decorator.DiscountCodeRequest;
import com.trs.cc.discountcode.decorator.DiscountCodeResponse;
import com.trs.cc.discountcode.decorator.DiscountCodeUseRequest;
import com.trs.cc.discountcode.exception.*;
import com.trs.cc.discountcode.model.DiscountCode;
import com.trs.cc.discountcode.model.DiscountCodeLog;
import com.trs.cc.discountcode.repository.DiscountCodeLogRepository;
import com.trs.cc.discountcode.repository.DiscountCodeRepository;
import com.trs.cc.discountcode.utils.JwtTokenUtil;
import com.trs.cc.discountcode.utils.NullAwareBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        DiscountCode discountCode = getDiscountCode(discountCodeId);

        copyNotNullProps.copyProperties(discountCode, discountCodeRequest);
        discountCode = discountCodeRepository.save(discountCode);

        return modelMapper.map(discountCode, DiscountCodeResponse.class);
    }

    @Override
    public void deleteDiscountCode(String discountCodeId) throws NotFoundException {
        DiscountCode discountCode = getDiscountCode(discountCodeId);
        discountCode.setSoftDelete(true);
        discountCodeRepository.save(discountCode);
    }

    @Override
    public DiscountCode useDiscountCode(String module, DiscountCodeUseRequest discountCodeUseRequest) throws NotFoundException, AuthException, CodeUsageLimitException, TimeLimitExceedException {

        String userId = discountCodeUseRequest.getUserId();
        String discountCode = discountCodeUseRequest.getDiscountCode();

        // find discount code data by code
        DiscountCode discountCodeData = discountCodeRepository.findByDiscountCodeAndSoftDeleteIsFalse(discountCode).orElseThrow(()->new NotFoundException(ExceptionConstant.DISCOUNT_CODE_NOT_EXISTS));

        // check expiration
        Date currentDate = new Date();
        Date startDate = discountCodeData.getStartDate();
        Date expirationDate = discountCodeData.getExpirationDate();

        if(startDate!=null && currentDate.compareTo(startDate)<0){
            String dateTime = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(startDate);
            throw new TimeLimitExceedException(String.format(ExceptionConstant.YOU_CAN_NOT_USE_DISCOUNT_CODE_BEFORE_OF_DATE,dateTime));
        }
        if (expirationDate!=null && currentDate.compareTo(expirationDate)>0) {
            throw new TimeLimitExceedException(ExceptionConstant.DISCOUNT_CODE_EXPIRED);
        }

        // check user access if any exists
        List<String> users = discountCodeData.getUsers();
        if (users != null && !users.isEmpty() && !users.contains(userId)) { // if users specification exists and user not exists
            throw new AuthException(ExceptionConstant.USER_NOT_ALLOWED);
        }

        // check modules permission
        List<String> modules = discountCodeData.getModules();
        modules = modules == null ? new ArrayList<>() : modules;
        if (!modules.contains(module)) {
            throw new AuthException(ExceptionConstant.MODULE_NOT_HAVE_ACCESS);
        }

        // check usage and update usage
        int maxUse = discountCodeData.getMaxUsage();
        int used = discountCodeData.getCurrentUsage();
        if(maxUse>0) {
            if (maxUse <= used) {
                throw new CodeUsageLimitException(ExceptionConstant.DISCOUNT_CODE_USAGE_LIMIT_EXCEEDS);
            }
        }
        DiscountCodeLog discountCodeLog = new DiscountCodeLog(null, discountCodeData.getId(), userId, module);
        discountCodeLogRepository.save(discountCodeLog);

        return discountCodeData;
    }

    @Override
    public List<DiscountCode> getDiscountCodeList() {
        return discountCodeRepository.findAllBySoftDeleteIsFalse();
    }

    @Override
    public DiscountCode getDiscountCode(String discountCodeId) throws NotFoundException {
        return discountCodeRepository.findByIdAndSoftDeleteIsFalse(discountCodeId).orElseThrow(()->new NotFoundException(ExceptionConstant.DISCOUNT_CODE_NOT_EXISTS));
    }

    @Override
    public DiscountCode save(DiscountCode discountCode) {
        return discountCodeRepository.save(discountCode);
    }
}

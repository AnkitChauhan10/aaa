package com.trs.cc.discountcode.aspects;

import com.trs.cc.discountcode.utils.DescriptionParameter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ResponseToLocaleAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.trs.cc.discountcode.services.ResponseManager.getResponse(..))")
    Object aroundSendResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(".....info....");
        Object[] responseMethodArguments = joinPoint.getArgs();
        for (Object argument : responseMethodArguments) {
            logger.info((String) argument);
            if (argument.getClass().isAnnotationPresent(DescriptionParameter.class)) {
                logger.info((String) argument);
            }
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }

    @Before("execution(* com.trs.cc.discountcode.services.ResponseManager.getResponse(..))")
    void aroundSendResponse(JoinPoint joinPoint) {

        logger.info(".....before....");
    }
}

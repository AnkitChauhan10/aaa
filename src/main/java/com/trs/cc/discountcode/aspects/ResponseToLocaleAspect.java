package com.trs.cc.discountcode.aspects;

import com.trs.cc.discountcode.decorator.RequestSession;
import com.trs.cc.discountcode.utils.DescriptionParameter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.FieldSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Aspect
@Configuration
public class ResponseToLocaleAspect {

    @Autowired
    MessageSource messageSource;
    @Autowired
    RequestSession requestSession;


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.trs.cc.discountcode.services.ResponseManager.getResponse(..))")
    Object aroundSendResponse(ProceedingJoinPoint joinPoint) throws Throwable {

        // get annotations list
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        // get arguments
        Object[] arguments = joinPoint.getArgs();

        int index = 0; // index for get argument
        for(Annotation[] annotations : parameterAnnotations) {
            Object argument = arguments[index];

            for (Annotation annotation : annotations) {
                // change description if description annotation found
                if (annotation instanceof DescriptionParameter) {
                    logger.info("Message -> {}",argument);
                    logger.info("Locale -> {}",requestSession.getLocale());
                    arguments[index] = messageSource.getMessage(argument.toString(), null, requestSession.getLocale());
                }
            }

            index += 1;
        }

        // re-initialize arguments
        return joinPoint.proceed(arguments);

    }
}

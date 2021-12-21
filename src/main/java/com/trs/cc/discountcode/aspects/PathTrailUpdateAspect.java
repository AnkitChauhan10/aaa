package com.trs.cc.discountcode.aspects;


import com.trs.cc.discountcode.decorator.RequestSession;
import com.trs.cc.discountcode.model.PathTrail;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Date;

@Aspect
@Configuration
public class PathTrailUpdateAspect {

    @Autowired
    ApplicationContext applicationContext;


    @Around("execution(* com.trs.cc.discountcode.repository.*.save(..))")
    Object pathTrailArround(ProceedingJoinPoint joinPoint) throws Throwable{
        if(RequestContextHolder.getRequestAttributes() == null){
            return joinPoint.proceed(joinPoint.getArgs());
        }
        RequestSession requestSession = applicationContext.getBean(RequestSession.class);
        if(joinPoint.getArgs().length ==  1 ){
            Object pathTrail = joinPoint.getArgs()[0];
            if(pathTrail instanceof PathTrail){
                PathTrail pathTrail1 = (PathTrail) pathTrail;
                if(pathTrail1.getCreated() == null){
                    if(requestSession.getJwtUser() != null){
                        pathTrail1.setCreatedBy(requestSession.getJwtUser().getId());
                    }
                    pathTrail1.setCreated(new Date());
                }
                if(requestSession.getJwtUser() != null){
                    pathTrail1.setUpdatedBy(requestSession.getJwtUser().getId());
                }
                pathTrail1.setUpdated(new Date());
            }
            return joinPoint.proceed(new Object[]{pathTrail});
        }else{
            return joinPoint.proceed(joinPoint.getArgs());
        }
    }
}
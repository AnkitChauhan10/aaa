package com.trs.cc.discountcode.aspects;


import com.trs.cc.discountcode.decorator.RequestSession;
import com.trs.cc.discountcode.model.PathTrail;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Aspect
@Configuration
public class PathTrailUpdateAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RequestSession requestSession;




    @Around("execution(* com.trs.cc.discountcode.repository.*.save(..))")
    Object pathTrailAround(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info("Session Id (IN Aspect) :"+requestSession.getR());
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
